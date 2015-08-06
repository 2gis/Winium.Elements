namespace Winium.Elements.Desktop.Tests
{
    #region using

    using System.Collections.Generic;

    using Moq;
    using Moq.Protected;

    using NUnit.Framework;

    using OpenQA.Selenium;
    using OpenQA.Selenium.Remote;

    #endregion

    [TestFixture]
    public class ComboBoxTests
    {
        #region Public Methods and Operators

        [Test]
        public void IsComboBoxExpanded()
        {
            var driverMock = new DriverMocked();

            driverMock.Protected()
                .Setup<Response>("Execute", "isComboBoxExpanded", ItExpr.IsAny<Dictionary<string, object>>())
                .Returns(Response.FromJson(@"{value: ""True""}"));

            var elementMock = new Mock<RemoteWebElement>(driverMock.Object, "comboBoxElementId");

            var comboBox = elementMock.Object.ToComboBox();

            Assert.That(comboBox.IsExpanded, Is.EqualTo(true));
        }

        [Test]
        public void ScrollToComboBoxItem()
        {
            var driverMock = new DriverMocked();

            driverMock.Protected()
                .Setup<Response>("Execute", "scrollToComboBoxItem", ItExpr.IsAny<Dictionary<string, object>>())
                .Returns(Response.FromJson(@"{value: {ELEMENT: ""comboBoxItemElementId""}}"));

            var elementMock = new Mock<RemoteWebElement>(driverMock.Object, "comboBoxElementId");

            var comboBox = elementMock.Object.ToComboBox();

            Assert.That(comboBox.ScrollTo(By.Id("comboBoxItemElementId")), Is.TypeOf(typeof(RemoteWebElement)));
        }

        #endregion
    }
}
