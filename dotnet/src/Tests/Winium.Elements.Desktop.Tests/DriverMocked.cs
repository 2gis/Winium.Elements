namespace Winium.Elements.Desktop.Tests
{
    #region using

    using System.Collections.Generic;

    using Moq;
    using Moq.Protected;

    using OpenQA.Selenium;
    using OpenQA.Selenium.Remote;

    #endregion

    public class DriverMocked : Mock<RemoteWebDriver>
    {
        #region Constructors and Destructors

        public DriverMocked()
            : base(new Mock<ICommandExecutor>().Object, new Mock<ICapabilities>().Object)
        {
            this.Protected()
                .Setup<Response>("Execute", "newSession", ItExpr.IsAny<Dictionary<string, object>>())
                .Returns(Response.FromJson(@"{sessionId : ""AvesomeSession""}"));
        }

        #endregion
    }
}
