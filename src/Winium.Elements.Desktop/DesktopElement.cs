namespace Winium.Elements.Desktop
{
    #region using

    using System;
    using System.Collections.ObjectModel;
    using System.Drawing;

    using OpenQA.Selenium;
    using OpenQA.Selenium.Remote;

    #endregion

    public abstract class DesktopElement : IWebElement
    {
        #region Fields

        protected RemoteWebElement WrappedElement;

        #endregion

        #region Constructors and Destructors

        protected DesktopElement(IWebElement element)
        {
            this.WrappedElement = element as RemoteWebElement;
            if (this.WrappedElement == null)
            {
                throw new InvalidCastException("Specified cast is not valid. Please use RemoteWebElement as parameter.");
            }
        }

        #endregion

        #region Public Properties

        public bool Displayed
        {
            get
            {
                return this.WrappedElement.Displayed;
            }
        }

        public bool Enabled
        {
            get
            {
                return this.WrappedElement.Enabled;
            }
        }

        public Point Location
        {
            get
            {
                return this.WrappedElement.Location;
            }
        }

        public bool Selected
        {
            get
            {
                return this.WrappedElement.Selected;
            }
        }

        public Size Size
        {
            get
            {
                return this.WrappedElement.Size;
            }
        }

        public string TagName
        {
            get
            {
                return this.WrappedElement.TagName;
            }
        }

        public string Text
        {
            get
            {
                return this.WrappedElement.Text;
            }
        }

        #endregion

        #region Public Methods and Operators

        public void Clear()
        {
            this.WrappedElement.Clear();
        }

        public void Click()
        {
            this.WrappedElement.Click();
        }

        public IWebElement FindElement(By @by)
        {
            return this.WrappedElement.FindElement(@by);
        }

        public ReadOnlyCollection<IWebElement> FindElements(By @by)
        {
            return this.WrappedElement.FindElements(@by);
        }

        public string GetAttribute(string attributeName)
        {
            return this.WrappedElement.GetAttribute(attributeName);
        }

        public string GetCssValue(string propertyName)
        {
            return this.WrappedElement.GetCssValue(propertyName);
        }

        public void SendKeys(string text)
        {
            this.WrappedElement.SendKeys(text);
        }

        public void Submit()
        {
            this.WrappedElement.Submit();
        }

        #endregion
    }
}
