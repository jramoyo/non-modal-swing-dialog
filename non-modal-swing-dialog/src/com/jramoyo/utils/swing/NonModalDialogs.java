/*
 * Copyright (c) 2012, Jan Amoyo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions 
 * are met:
 *
 * - Redistributions of source code must retain the above copyright 
 *   notice, this list of conditions and the following disclaimer.
 * 
 * - Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer 
 *   in the documentation and/or other materials provided with the
 *   distribution.
 *   
 * - Neither the name of the authors nor the names of its contributors 
 *   may be used to endorse or promote products derived from this software 
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS 
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE 
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, 
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS 
 * OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED 
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, 
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF 
 * THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH 
 * DAMAGE.
 *
 * NonModalDialogs.java
 * Sep 13, 2012
 */
package com.jramoyo.utils.swing;

import java.awt.Component;
import java.util.concurrent.TimeUnit;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * NonModalDialogs
 * <p>
 * This class contains various methods for creating non-modal Swing dialogs. It
 * acts as an extension to <code>JOptionPane</code>
 * </p>
 * 
 * @author jramoyo
 */
public final class NonModalDialogs {

	/**
	 * Brings up a dialog with the options Yes, No and Cancel; with the title,
	 * "Select an Option".
	 * 
	 * @param message
	 *            the Object to display
	 * @param messageType
	 *            the type of message to be displayed: ERROR_MESSAGE,
	 *            INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE, or
	 *            PLAIN_MESSAGE
	 * @param yesBlock
	 *            the Runnable to execute if "Yes" was selected
	 * @param noBlock
	 *            the Runnable to execute if "No" was selected
	 * @param cancelBlock
	 *            the Runnable to execute if "Cancel" was selected
	 */
	public static void exectuteOnYesNoCancelConfirmDialog(Object message,
			int messageType, Runnable yesBlock, Runnable noBlock,
			Runnable cancelBlock) {
		exectuteOnYesNoCancelConfirmDialog(message, "Select an Option",
				messageType, yesBlock, noBlock, cancelBlock);
	}

	/**
	 * Brings up a dialog with the options Yes, No and Cancel; with the title,
	 * "Select an Option".
	 * 
	 * @param message
	 *            the Object to display
	 * @param yesBlock
	 *            the Runnable to execute if "Yes" was selected
	 * @param noBlock
	 *            the Runnable to execute if "No" was selected
	 * @param cancelBlock
	 *            the Runnable to execute if "Cancel" was selected
	 */
	public static void exectuteOnYesNoCancelConfirmDialog(Object message,
			Runnable yesBlock, Runnable noBlock, Runnable cancelBlock) {
		exectuteOnYesNoCancelConfirmDialog(message, "Select an Option",
				JOptionPane.QUESTION_MESSAGE, yesBlock, noBlock, cancelBlock);
	}

	/**
	 * Brings up a dialog with the options Yes, No and Cancel.
	 * 
	 * @param message
	 *            the Object to display
	 * @param title
	 *            the title string for the dialog
	 * @param messageType
	 *            the type of message to be displayed: ERROR_MESSAGE,
	 *            INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE, or
	 *            PLAIN_MESSAGE
	 * @param yesBlock
	 *            the Runnable to execute if "Yes" was selected
	 * @param noBlock
	 *            the Runnable to execute if "No" was selected
	 * @param cancelBlock
	 *            the Runnable to execute if "Cancel" was selected
	 */
	public static void exectuteOnYesNoCancelConfirmDialog(Object message,
			String title, int messageType, Runnable yesBlock, Runnable noBlock,
			Runnable cancelBlock) {
		Thread thread = new Thread(new YesNoCancelRunnable(message, title,
				messageType, yesBlock, noBlock, cancelBlock),
				"Confirm Dialog Thread");
		thread.start();
	}

	/**
	 * Brings up a dialog with the options Yes, No and Cancel.
	 * 
	 * @param message
	 *            the Object to display
	 * @param title
	 *            the title string for the dialog
	 * @param yesBlock
	 *            the Runnable to execute if "Yes" was selected
	 * @param noBlock
	 *            the Runnable to execute if "No" was selected
	 * @param cancelBlock
	 *            the Runnable to execute if "Cancel" was selected
	 */
	public static void exectuteOnYesNoCancelConfirmDialog(Object message,
			String title, Runnable yesBlock, Runnable noBlock,
			Runnable cancelBlock) {
		exectuteOnYesNoCancelConfirmDialog(message, title,
				JOptionPane.QUESTION_MESSAGE, yesBlock, noBlock, cancelBlock);
	}

	/**
	 * Brings up a dialog with the options Yes and No; with the title, "Select
	 * an Option".
	 * 
	 * @param message
	 *            the Object to display
	 * @param messageType
	 *            the type of message to be displayed: ERROR_MESSAGE,
	 *            INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE, or
	 *            PLAIN_MESSAGE
	 * @param yesBlock
	 *            the Runnable to execute if "Yes" was selected
	 * @param noBlock
	 *            the Runnable to execute if "No" was selected
	 */
	public static void exectuteOnYesNoConfirmDialog(Object message,
			int messageType, Runnable yesBlock, Runnable noBlock) {
		exectuteOnYesNoConfirmDialog(message, "Select an Option", messageType,
				yesBlock, noBlock);
	}

	/**
	 * Brings up a dialog with the options Yes and No; with the title, "Select
	 * an Option".
	 * 
	 * @param message
	 *            the Object to display
	 * @param yesBlock
	 *            the Runnable to execute if "Yes" was selected
	 * @param noBlock
	 *            the Runnable to execute if "No" was selected
	 */
	public static void exectuteOnYesNoConfirmDialog(Object message,
			Runnable yesBlock, Runnable noBlock) {
		exectuteOnYesNoConfirmDialog(message, "Select an Option",
				JOptionPane.QUESTION_MESSAGE, yesBlock, noBlock);
	}

	/**
	 * Brings up a dialog with the options Yes and No.
	 * 
	 * @param message
	 *            the Object to display
	 * @param title
	 *            the title string for the dialog
	 * @param messageType
	 *            the type of message to be displayed: ERROR_MESSAGE,
	 *            INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE, or
	 *            PLAIN_MESSAGE
	 * @param yesBlock
	 *            the Runnable to execute if "Yes" was selected
	 * @param noBlock
	 *            the Runnable to execute if "No" was selected
	 */
	public static void exectuteOnYesNoConfirmDialog(Object message,
			String title, int messageType, Runnable yesBlock, Runnable noBlock) {
		Thread thread = new Thread(new YesNoRunnable(message, title,
				messageType, yesBlock, noBlock), "Confirm Dialog Thread");
		thread.start();
	}

	/**
	 * Brings up a dialog with the options Yes and No.
	 * 
	 * @param message
	 *            the Object to display
	 * @param title
	 *            the title string for the dialog
	 * @param yesBlock
	 *            the Runnable to execute if "Yes" was selected
	 * @param noBlock
	 *            the Runnable to execute if "No" was selected
	 */
	public static void exectuteOnYesNoConfirmDialog(Object message,
			String title, Runnable yesBlock, Runnable noBlock) {
		exectuteOnYesNoConfirmDialog(message, title,
				JOptionPane.QUESTION_MESSAGE, yesBlock, noBlock);
	}

	/**
	 * Brings up a non-modal information message dialog titled "Message"
	 * 
	 * @param parentComponent
	 *            determines the Frame in which the dialog is displayed; if
	 *            null, or if the parentComponent has no Frame, a default Frame
	 *            is used
	 * @param message
	 *            the Object to display
	 */
	public static void showNonModalMessageDialog(Component parentComponent,
			Object message) {
		showNonModalMessageDialog(parentComponent, message, "Message",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Brings up a non-modal information message dialog
	 * 
	 * @param parentComponent
	 *            determines the Frame in which the dialog is displayed; if
	 *            null, or if the parentComponent has no Frame, a default Frame
	 *            is used
	 * @param message
	 *            the Object to display
	 * @param title
	 *            the title string for the dialog
	 * @param messageType
	 *            the type of message to be displayed: ERROR_MESSAGE,
	 *            INFORMATION_MESSAGE, WARNING_MESSAGE, QUESTION_MESSAGE, or
	 *            PLAIN_MESSAGE
	 */
	public static void showNonModalMessageDialog(Component parentComponent,
			Object message, String title, int messageType) {
		JOptionPane pane = createJOptionPane(message, messageType);
		JDialog dialog = pane.createDialog(parentComponent, title);
		dialog.pack();
		dialog.setModal(false);

		dialog.setVisible(true);
	}

	/*
	 * Factory method for creating a JOptionPane given a message and message
	 * type
	 */
	private static JOptionPane createJOptionPane(Object message, int messageType) {
		return new JOptionPane(message, messageType);
	}

	/*
	 * Factory method for creating a JOptionPane given a message, message type,
	 * and option type
	 */
	private static JOptionPane createJOptionPane(Object message,
			int messageType, int optionType) {
		return new JOptionPane(message, messageType, optionType);
	}

	/*
	 * Runnable for launching a Dialog via JOptionPane.
	 */
	private static class DialogLauncher implements Runnable {
		private final JOptionPane jOptionPane;
		private final String title;

		private DialogLauncher(JOptionPane jOptionPane, String title) {
			this.jOptionPane = jOptionPane;
			this.title = title;
		}

		public void run() {
			JDialog dialog = jOptionPane.createDialog(null, title);
			jOptionPane.selectInitialValue();
			dialog.setModal(false);
			dialog.setVisible(true);
		}
	}

	/*
	 * Runnable for a Yes-No-Cancel confirm dialog
	 */
	private static final class YesNoCancelRunnable implements Runnable {
		private final Object message;
		private final String title;
		private final int messageType;
		private final Runnable yesBlock;
		private final Runnable noBlock;
		private final Runnable cancelBlock;

		private YesNoCancelRunnable(Object message, String title,
				int messageType, Runnable yesBlock, Runnable noBlock,
				Runnable cancelBlock) {
			this.message = message;
			this.title = title;
			this.messageType = messageType;
			this.yesBlock = yesBlock;
			this.noBlock = noBlock;
			this.cancelBlock = cancelBlock;
		}

		public void run() {
			JOptionPane jOptionPane = createJOptionPane(message, messageType,
					JOptionPane.YES_NO_CANCEL_OPTION);
			SwingUtilities.invokeLater(new DialogLauncher(jOptionPane, title));
			while (!(jOptionPane.getValue() instanceof Integer)) {
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}

			Runnable choiceBlock = null;
			switch (((Integer) jOptionPane.getValue()).intValue()) {
			case JOptionPane.YES_OPTION:
				choiceBlock = yesBlock;
				break;
			case JOptionPane.NO_OPTION:
				choiceBlock = noBlock;
				break;
			case JOptionPane.CANCEL_OPTION:
				choiceBlock = cancelBlock;
				break;
			}

			SwingUtilities.invokeLater(choiceBlock);
		}
	}

	/*
	 * Runnable for a Yes-No confirm dialog
	 */
	private static final class YesNoRunnable implements Runnable {
		private final Object message;
		private final String title;
		private final int messageType;
		private final Runnable yesBlock;
		private final Runnable noBlock;

		private YesNoRunnable(Object message, String title, int messageType,
				Runnable yesBlock, Runnable noBlock) {
			this.message = message;
			this.title = title;
			this.messageType = messageType;
			this.yesBlock = yesBlock;
			this.noBlock = noBlock;
		}

		public void run() {
			JOptionPane jOptionPane = createJOptionPane(message, messageType,
					JOptionPane.YES_NO_CANCEL_OPTION);
			SwingUtilities.invokeLater(new DialogLauncher(jOptionPane, title));
			while (!(jOptionPane.getValue() instanceof Integer)) {
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}

			Runnable choiceBlock = null;
			switch (((Integer) jOptionPane.getValue()).intValue()) {
			case JOptionPane.YES_OPTION:
				choiceBlock = yesBlock;
				break;
			case JOptionPane.NO_OPTION:
				choiceBlock = noBlock;
				break;
			}

			SwingUtilities.invokeLater(choiceBlock);
		}
	}
}
