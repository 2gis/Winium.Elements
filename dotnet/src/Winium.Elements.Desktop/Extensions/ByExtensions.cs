namespace Winium.Elements.Desktop.Extensions
{
    #region using

    using System.Linq;
    using System.Text.RegularExpressions;

    using OpenQA.Selenium;

    #endregion

    public static class ByExtensions
    {
        #region Constants

        private const string DescriptionRegexp = @"\.(.*)\: (.*)";

        #endregion

        #region Public Methods and Operators

        public static object GetStrategy(this By by)
        {
            var match = Regex.Match(by.ToString(), DescriptionRegexp);
            return Regex.Replace(match.Groups[1].Value, "([A-Z])", " $1").Split('[').First().Trim().ToLower();
        }

        public static object GetValue(this By by)
        {
            var match = Regex.Match(by.ToString(), DescriptionRegexp);
            return match.Groups[2].Value;
        }

        #endregion
    }
}
