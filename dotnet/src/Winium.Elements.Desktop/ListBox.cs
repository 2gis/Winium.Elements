namespace Winium.Elements.Desktop
{
    #region using

    using System.Collections.Generic;

    using OpenQA.Selenium;
    using OpenQA.Selenium.Remote;

    using Winium.Elements.Desktop.Extensions;

    #endregion

    public class ListBox : DesktopElement
    {
        #region Constants

        private const string ScrollToListBoxItem = "scrollToListBoxItem";

        #endregion

        #region Constructors and Destructors

        public ListBox(IWebElement element)
            : base(element)
        {
        }

        #endregion

        #region Public Methods and Operators

        public RemoteWebElement ScrollTo(By by)
        {
            var response = this.Execute(
                ScrollToListBoxItem,
                new Dictionary<string, object>
                    {
                        { "id", this.Id },
                        { "using", by.GetStrategy() },
                        { "value", by.GetValue() },
                    });

            return this.CreateRemoteWebElementFromResponse(response);
        }

        #endregion
    }
}
