# Non-modal Swing Dialogs
[![Build Status](https://travis-ci.org/jramoyo/non-modal-swing-dialog.png?branch=master)](https://travis-ci.org/jramoyo/non-modal-swing-dialog)

## Abstract
`JOptionPane` provides various methods to easily create message dialogs in Swing. A limitation to this, is that all dialogs created from `JOptionPane` are modal. 

`JOptionPane` dialogs are modal because the UI thread needs to wait for a user input in order for processing to continue -- this is especially true for confirm dialogs.

In some applications, a UI component launched outside of Swing's _Event Dispatch Thread_ (EDT) may cover an active modal dialog -- this will render the UI inaccessible, making the application seem hanging.

The purpose of this library is to address this limitation to `JOptionPane` by providing a class (`NonModalDialogs`) which provides various methods for creating non-modal message dialogs in Swing.

`NonModalDialogs` works by handling the user input on a separate thread, asynchronous to the application thread.

Details on this class are available via [Javadoc](https://non-modal-swing-dialog.googlecode.com/svn/javadoc/index.html).

### Maven
```xml
<dependency>
  <groupId>com.jramoyo</groupId>
  <artifactId>non-modal-swing-dialog</artifactId>
  <version>1.2</version>
</dependency>
```

### Example
The below code brings-up a non-modal Yes-No-Cancel confirm dialog. Depending on the option selected, the corresponding `Runnable` will be executed asynchronously.

```java
NonModalDialogs.exectuteOnYesNoCancelConfirmDialog("Are you ready?",
	new Runnable() {
		// 'Yes' action
		@Override
		public void run() {
			JOptionPane.showMessageDialog(null, "Let's go!");
		}
	}, new Runnable() {
		// 'No' action
		@Override
		public void run() {
			JOptionPane.showMessageDialog(null,
				"I'll wait for you then.");
		}
	}, new Runnable() {
		// 'Cancel' action
		@Override
		public void run() {
			JOptionPane.showMessageDialog(null, "Nevermind.");
		}
	});
```

Starting version 1.1, the `Runnable` actions are executed on Swing's EDT. To ensure liveliness of the UI, execute long-running tasks on a separate thread (ideally via [SwingWorker](http://docs.oracle.com/javase/6/docs/api/javax/swing/SwingWorker.html)).
