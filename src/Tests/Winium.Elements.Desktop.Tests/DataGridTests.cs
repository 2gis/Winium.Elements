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
    public class DataGridTests
    {
        #region Public Methods and Operators

        [Test]
        public void GetDataGridCell()
        {
            var driverMock = new Mock<RemoteWebDriver>(
                new Mock<ICommandExecutor>().Object,
                new Mock<ICapabilities>().Object);

            driverMock.Protected()
                .Setup<Response>("Execute", "getDataGridCell", ItExpr.IsAny<Dictionary<string, object>>())
                .Returns(Response.FromJson(@"{value: {ELEMENT: ""dGridCellElementId""}}"));

            driverMock.Protected()
                .Setup<Response>("Execute", "newSession", ItExpr.IsAny<Dictionary<string, object>>())
                .Returns(Response.FromJson(@"{sessionId : ""AvesomeSession""}"));

            var elementMock = new Mock<RemoteWebElement>(driverMock.Object, "dGridElementId");

            var dataGrid = elementMock.Object.ToDataGrid();

            var cell = dataGrid.GetCell(1, 1);

            Assert.That(cell, Is.TypeOf(typeof(RemoteWebElement)));
        }

        #endregion
    }
}
