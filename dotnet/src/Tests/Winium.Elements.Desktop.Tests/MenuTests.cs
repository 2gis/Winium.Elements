namespace Winium.Elements.Desktop.Tests
{
    #region using

    using System.Collections.Generic;

    using Moq;
    using Moq.Protected;

    using NUnit.Framework;

    using OpenQA.Selenium.Remote;

    using Winium.Elements.Desktop.Extensions;

    #endregion

    [TestFixture]
    public class MenuTests
    {
        #region Public Methods and Operators

        [Test]
        public void GetMenuItem()
        {
            var driverMock = new DriverMocked();

            driverMock.Protected()
                .Setup<Response>("Execute", "findMenuItem", ItExpr.IsAny<Dictionary<string, object>>())
                .Returns(Response.FromJson(@"{value: {ELEMENT: ""menuItemElementId""}}"));

            var elementMock = new Mock<RemoteWebElement>(driverMock.Object, "menutId");

            var menu = elementMock.Object.ToMenu();

            Assert.That(menu.FindItem("Level1$Level2"), Is.TypeOf(typeof(RemoteWebElement)));
        }

        [Test]
        public void SelectMenuItem()
        {
            var driverMock = new DriverMocked();

            driverMock.Protected()
                .Setup<Response>("Execute", "selectMenuItem", ItExpr.IsAny<Dictionary<string, object>>())
                .Returns(Response.FromJson(@"{value: {ELEMENT: ""menuItemElementId""}}"));

            var elementMock = new Mock<RemoteWebElement>(driverMock.Object, "menutId");

            var menu = elementMock.Object.ToMenu();

            Assert.That(menu.SelectItem("Level1$Level2"), Is.TypeOf(typeof(RemoteWebElement)));
        }

        #endregion
    }
}
