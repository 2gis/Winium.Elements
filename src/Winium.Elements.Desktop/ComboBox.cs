namespace Winium.Elements.Desktop
{
    #region using

    using System.Collections.Generic;

    using OpenQA.Selenium;
    using OpenQA.Selenium.Remote;

    using Winium.Elements.Desktop.Extensions;

    #endregion

    public class ComboBox : DesktopElement
    {
        #region Constants

        private const string CollapseComboBox = "collapseComboBox";

        private const string ExpandComboBox = "expandComboBox";

        private const string FindComboBoxSelectedItem = "findComboBoxSelectedItem";

        private const string IsComboBoxExpanded = "isComboBoxExpanded";

        private const string ScrollToComboBoxItem = "scrollToComboBoxItem";

        #endregion

        #region Constructors and Destructors

        static ComboBox()
        {
            CommandInfoRepository.Instance.TryAddCommand(
                IsComboBoxExpanded,
                new CommandInfo("POST", "/session/{sessionId}/element/{id}/combobox/expanded"));

            CommandInfoRepository.Instance.TryAddCommand(
                ExpandComboBox,
                new CommandInfo("POST", "/session/{sessionId}/element/{id}/combobox/expand"));

            CommandInfoRepository.Instance.TryAddCommand(
                CollapseComboBox,
                new CommandInfo("POST", "/session/{sessionId}/element/{id}/combobox/collapse"));

            CommandInfoRepository.Instance.TryAddCommand(
                FindComboBoxSelectedItem,
                new CommandInfo("POST", "/session/{sessionId}/element/{id}/combobox/item/selected"));

            CommandInfoRepository.Instance.TryAddCommand(
                ScrollToComboBoxItem,
                new CommandInfo("POST", "/session/{sessionId}/element/{id}/combobox/scroll"));
        }

        public ComboBox(IWebElement element)
            : base(element)
        {
        }

        #endregion

        #region Public Properties

        public bool IsExpanded
        {
            get
            {
                var parameters = new Dictionary<string, object> { { "id", this.Id } };
                var response = this.Execute(IsComboBoxExpanded, parameters);

                return bool.Parse(response.Value.ToString());
            }
        }

        #endregion

        #region Public Methods and Operators

        public void Collapse()
        {
            this.CallComboBoxCommand(CollapseComboBox);
        }

        public void Expand()
        {
            this.CallComboBoxCommand(ExpandComboBox);
        }

        public RemoteWebElement FindSelected(int row, int column)
        {
            return this.CreateRemoteWebElementFromResponse(this.CallComboBoxCommand(FindComboBoxSelectedItem));
        }

        public RemoteWebElement ScrollTo(By by)
        {
            var response = this.Execute(
                ScrollToComboBoxItem,
                new Dictionary<string, object>
                    {
                        { "id", this.Id },
                        { "using", by.GetStrategy() },
                        { "value", by.GetValue() },
                    });

            return this.CreateRemoteWebElementFromResponse(response);
        }

        #endregion

        #region Methods

        private Response CallComboBoxCommand(string command)
        {
            var parameters = new Dictionary<string, object> { { "id", this.Id } };
            return this.Execute(command, parameters);
        }

        #endregion
    }
}
