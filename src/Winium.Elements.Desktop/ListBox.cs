namespace Winium.Elements.Desktop
{
    #region using

    using System.Collections.Generic;
    using System.Linq;
    using System.Text.RegularExpressions;

    using OpenQA.Selenium;
    using OpenQA.Selenium.Remote;

    #endregion

    public class ListBox : DesktopElement
    {
        #region Constants

        private const string ScrollToListBoxItem = "scrollToListBoxItem";

        #endregion

        #region Constructors and Destructors

        static ListBox()
        {
            CommandInfoRepository.Instance.TryAddCommand(
                ScrollToListBoxItem,
                new CommandInfo("POST", "/session/{sessionId}/element/{id}/listbox/scroll"));
        }

        public ListBox(IWebElement element)
            : base(element)
        {
        }

        #endregion

        #region Public Methods and Operators

        public RemoteWebElement ScrollTo(By by)
        {
            var match = Regex.Match(by.ToString(), @"\.(.*)\: (.*)");
            var strategy = Regex.Replace(match.Groups[1].Value, "([A-Z])", " $1").Split('[').First().Trim().ToLower();
            var value = match.Groups[2].Value;

            var response = this.Execute(
                ScrollToListBoxItem,
                new Dictionary<string, object> { { "id", this.Id }, { "using", strategy }, { "value", value }, });

            return this.CreateRemoteWebElementFromResponse(response);
        }

        #endregion
    }
}
