# Winium.Elements
[![GitHub license](https://img.shields.io/badge/license-MPL 2.0-blue.svg?style=flat-square)](LICENSE)

<p align="center">
<img src="https://raw.githubusercontent.com/2gis/Winium.StoreApps/assets/winium.png" alt="Winium.Desktop is Selenium Remote WebDriver implementation for automated testing of Windows application based on WinFroms and WPF platforms">
</p>

Winium.Elements provides helpful elements to easily work with specific desktop controls such as DataGrid or ComboBox.

## Quick Start
1. Create test project using [Winium Desktop Driver](https://github.com/2gis/Winium.Desktop).
2. Find element and convert it to [ElementType] using To[ElementType] method.
	
	```cs
	var element = driver.FindElement(By.Id("combo_box1"));
	var comboBox = element.ToComboBox();
	```
	After that using extension methods. For example
	```cs
	comboBox.Expand();
	Assert.IsTrue(comboBox.IsExpanded);
	```

## How it works
Winium.Elements extend OpenQA.Selenium bindings for using additional methods Winium.Desktop driver such as "getDataGridCell". 
