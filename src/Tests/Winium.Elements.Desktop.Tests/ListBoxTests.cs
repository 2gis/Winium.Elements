namespace Winium.Elements.Desktop.Tests
{
    #region using

    using System.Collections.Generic;

    using Moq;
    using Moq.Protected;

    using NUnit.Framework;

    using OpenQA.Selenium;
    using OpenQA.Selenium.Remote;

    using Winium.Elements.Desktop.Extensions;

    #endregion

    [TestFixture]
    public class ListBoxTests
    {
        #region Public Methods and Operators

        [Test]
        public void ScrollToListBoxItem()
        {
            var driverMock = new DriverMocked();

            driverMock.Protected()
                .Setup<Response>("Execute", "scrollToListBoxItem", ItExpr.IsAny<Dictionary<string, object>>())
                .Returns(Response.FromJson(@"{value: {ELEMENT: ""listBoxItemElementId""}}"));

            var elementMock = new Mock<RemoteWebElement>(driverMock.Object, "listBoxElementId");

            var listBox = elementMock.Object.ToListBox();

            Assert.That(listBox.ScrollTo(By.Id("listBoxItemElementId")), Is.TypeOf(typeof(RemoteWebElement)));
        }

        #endregion
    }
}
