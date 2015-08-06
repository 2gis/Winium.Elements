namespace Winium.Elements.Desktop
{
    #region using

    using System.Reflection;

    using OpenQA.Selenium;
    using OpenQA.Selenium.Remote;

    #endregion

    public static class WebElementExtensions
    {
        #region Public Methods and Operators

        public static DataGrid ToDataGrid(this IWebElement element)
        {
            return new DataGrid(element);
        }

        public static ListBox ToListBox(this IWebElement element)
        {
            return new ListBox(element);
        }

        public static ComboBox ToComboBox(this IWebElement element)
        {
            return new ComboBox(element);
        }

        #endregion

        #region Methods

        internal static Response Execute(this IWebElement element, params object[] parameters)
        {
            var methodInfo = element.GetType().GetMethod("Execute", BindingFlags.NonPublic | BindingFlags.Instance);
            return (Response)methodInfo.Invoke(element, parameters);
        }

        internal static string GetId(this IWebElement element)
        {
            var propertyInfo = element.GetType()
                .GetProperty("Id", BindingFlags.NonPublic | BindingFlags.Instance | BindingFlags.GetProperty);
            return propertyInfo.GetValue(element, null).ToString();
        }

        #endregion
    }
}
