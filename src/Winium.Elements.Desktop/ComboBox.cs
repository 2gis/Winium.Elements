namespace Winium.Elements.Desktop
{
    #region using

    using System.Collections.Generic;
    using System.Linq;
    using System.Text.RegularExpressions;

    using OpenQA.Selenium;
    using OpenQA.Selenium.Remote;

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
                new CommandInfo("POST", "/session/{sessionId}/element/{id}/combobox/selected/element"));

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
            var parameters = new Dictionary<string, object> { { "id", this.Id } };
            this.Execute(CollapseComboBox, parameters);
        }

        public void Expand()
        {
            var parameters = new Dictionary<string, object> { { "id", this.Id } };
            this.Execute(ExpandComboBox, parameters);
        }

        public RemoteWebElement GetSelected(int row, int column)
        {
            var parameters = new Dictionary<string, object> { { "id", this.Id } };
            var response = this.Execute(FindComboBoxSelectedItem, parameters);

            return this.CreateRemoteWebElementFromResponse(response);
        }

        public RemoteWebElement ScrollTo(By by)
        {
            var match = Regex.Match(by.ToString(), @"\.(.*)\: (.*)");
            var strategy = Regex.Replace(match.Groups[1].Value, "([A-Z])", " $1").Split('[').First().Trim().ToLower();
            var value = match.Groups[2].Value;

            var response = this.Execute(
                ScrollToComboBoxItem,
                new Dictionary<string, object> { { "id", this.Id }, { "using", strategy }, { "value", value }, });

            return this.CreateRemoteWebElementFromResponse(response);
        }

        #endregion
    }
}
