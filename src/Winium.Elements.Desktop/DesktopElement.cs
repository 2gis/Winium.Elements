namespace Winium.Elements.Desktop
{
    #region using

    using System;
    using System.Collections.Generic;

    using OpenQA.Selenium;
    using OpenQA.Selenium.Remote;

    #endregion

    public abstract class DesktopElement : RemoteWebElement
    {
        #region Constructors and Destructors

        protected DesktopElement(IWebElement element)
            : base(GetRemoteWebDriver(element), element.GetId())
        {
        }

        #endregion

        #region Methods

        protected RemoteWebElement CreateRemoteWebElementFromResponse(Response response)
        {
            var elementDictionary = response.Value as Dictionary<string, object>;
            if (elementDictionary == null)
            {
                return null;
            }

            return new RemoteWebElement((RemoteWebDriver)this.WrappedDriver, (string)elementDictionary["ELEMENT"]);
        }

        private static RemoteWebDriver GetRemoteWebDriver(IWebElement element)
        {
            var remoteWebElement = element as RemoteWebElement;
            if (remoteWebElement == null)
            {
                throw new InvalidCastException("Specified cast is not valid. Please use RemoteWebElement as parameter.");
            }

            return (RemoteWebDriver)remoteWebElement.WrappedDriver;
        }

        #endregion
    }
}
