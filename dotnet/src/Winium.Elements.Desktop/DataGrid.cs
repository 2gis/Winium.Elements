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

        private const string FindDataGridCell = "findDataGridCell";

        private const string GetDataGridColumnCount = "getDataGridColumnCount";

        private const string GetDataGridRowCount = "getDataGridRowCount";

        private const string ScrollToDataGridCell = "scrollToDataGridCell";

        private const string SelectDataGridCell = "selectDataGridCell";

        #endregion

        #region Constructors and Destructors

        static DataGrid()
        {
            CommandInfoRepository.Instance.TryAddCommand(
                FindDataGridCell,
                new CommandInfo("POST", "/session/{sessionId}/element/{id}/datagrid/cell/{row}/{column}"));

            CommandInfoRepository.Instance.TryAddCommand(
                GetDataGridColumnCount,
                new CommandInfo("POST", "/session/{sessionId}/element/{id}/datagrid/column/count"));

            CommandInfoRepository.Instance.TryAddCommand(
                GetDataGridRowCount,
                new CommandInfo("POST", "/session/{sessionId}/element/{id}/datagrid/row/count"));

            CommandInfoRepository.Instance.TryAddCommand(
                ScrollToDataGridCell,
                new CommandInfo("POST", "/session/{sessionId}/element/{id}/datagrid/scroll/{row}/{column}"));

            CommandInfoRepository.Instance.TryAddCommand(
                SelectDataGridCell,
                new CommandInfo("POST", "/session/{sessionId}/element/{id}/datagrid/select/{row}/{column}"));
        }

        public DataGrid(IWebElement element)
            : base(element)
        {
        }

        #endregion

        #region Public Properties

        public int ColumnCount
        {
            get
            {
                var parameters = new Dictionary<string, object> { { "id", this.Id } };
                var response = this.Execute(GetDataGridColumnCount, parameters);

                return int.Parse(response.Value.ToString());
            }
        }

        public int RowCount
        {
            get
            {
                var parameters = new Dictionary<string, object> { { "id", this.Id } };
                var response = this.Execute(GetDataGridRowCount, parameters);

                return int.Parse(response.Value.ToString());
            }
        }

        #endregion

        #region Public Methods and Operators

        public RemoteWebElement Find(int row, int column)
        {
            return this.CreateRemoteWebElementFromResponse(this.CallDataGridcellCommand(FindDataGridCell, row, column));
        }

        public void ScrollTo(int row, int column)
        {
            this.CallDataGridcellCommand(ScrollToDataGridCell, row, column);
        }

        public void Select(int row, int column)
        {
            this.CallDataGridcellCommand(SelectDataGridCell, row, column);
        }

        #endregion

        #region Methods

        private Response CallDataGridcellCommand(string command, int row, int column)
        {
            var parameters = new Dictionary<string, object> { { "id", this.Id }, { "row", row }, { "column", column } };
            return this.Execute(command, parameters);
        }

        #endregion
    }
}
