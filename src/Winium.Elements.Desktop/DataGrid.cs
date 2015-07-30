namespace Winium.Elements.Desktop
{
    #region using

    using System.Collections.Generic;

    using OpenQA.Selenium;
    using OpenQA.Selenium.Remote;

    #endregion

    public class DataGrid : DesktopElement
    {
        #region Constants

        private const string GetDataGridCell = "getDataGridCell";

        #endregion

        #region Constructors and Destructors

        static DataGrid()
        {
            CommandInfoRepository.Instance.TryAddCommand(
                GetDataGridCell,
                new CommandInfo("POST", "/session/{sessionId}/element/{id}/cell/{row}/{column}"));
        }

        public DataGrid(IWebElement element)
            : base(element)
        {
        }

        #endregion

        #region Public Methods and Operators

        public RemoteWebElement GetCell(int row, int column)
        {
            var parameters = new Dictionary<string, object>
                                 {
                                     { "id", this.WrappedElement.GetId() },
                                     { "row", row },
                                     { "column", column }
                                 };
            var response = this.WrappedElement.Execute(GetDataGridCell, parameters);

            var elementDictionary = response.Value as Dictionary<string, object>;
            if (elementDictionary == null)
            {
                return null;
            }

            return new RemoteWebElement(
                (RemoteWebDriver)this.WrappedElement.WrappedDriver,
                (string)elementDictionary["ELEMENT"]);
        }

        #endregion
    }
}
