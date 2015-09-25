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
    public class DataGridTests
    {
        #region Public Methods and Operators

        [Test]
        public void FindDataGridCell()
        {
            var driverMock = new DriverMocked();

            driverMock.Protected()
                .Setup<Response>("Execute", "findDataGridCell", ItExpr.IsAny<Dictionary<string, object>>())
                .Returns(Response.FromJson(@"{value: {ELEMENT: ""dGridCellElementId""}}"));

            var elementMock = new Mock<RemoteWebElement>(driverMock.Object, "dGridElementId");

            var dataGrid = elementMock.Object.ToDataGrid();

            var cell = dataGrid.Find(1, 1);

            Assert.That(cell, Is.TypeOf(typeof(RemoteWebElement)));
        }

        [Test]
        public void GetDataGridColumnCount()
        {
            var driverMock = new DriverMocked();

            driverMock.Protected()
                .Setup<Response>("Execute", "getDataGridColumnCount", ItExpr.IsAny<Dictionary<string, object>>())
                .Returns(Response.FromJson(@"{value: 2}"));

            var elementMock = new Mock<RemoteWebElement>(driverMock.Object, "dGridElementId");

            var dataGrid = elementMock.Object.ToDataGrid();

            Assert.That(dataGrid.ColumnCount, Is.EqualTo(2));
        }

        [Test]
        public void GetDataGridRowCount()
        {
            var driverMock = new DriverMocked();

            driverMock.Protected()
                .Setup<Response>("Execute", "getDataGridRowCount", ItExpr.IsAny<Dictionary<string, object>>())
                .Returns(Response.FromJson(@"{value: 2}"));

            var elementMock = new Mock<RemoteWebElement>(driverMock.Object, "dGridElementId");

            var dataGrid = elementMock.Object.ToDataGrid();

            Assert.That(dataGrid.RowCount, Is.EqualTo(2));
        }

        #endregion
    }

}
