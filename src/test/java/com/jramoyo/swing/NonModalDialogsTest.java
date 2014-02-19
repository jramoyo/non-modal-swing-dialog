/*
 * Copyright (c) 2011, Jan Amoyo
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
 * NonModalDialogsTest.java
 * Nov 9, 2012
 */
package com.jramoyo.swing;

import javax.swing.JOptionPane;

/**
 * NonModalDialogsTest
 * 
 * @author jramoyo
 */
public class NonModalDialogsTest {

	public static void main(String... args) {
		NonModalDialogs.exectuteOnYesNoCancelConfirmDialog("Are you ready?",
				new Runnable() {
					// 'Yes' action
					public void run() {
						JOptionPane.showMessageDialog(null, "Let's go!");
					}
				}, new Runnable() {
					// 'No' action
					public void run() {
						JOptionPane.showMessageDialog(null,
								"I'll wait for you then.");
					}
				}, new Runnable() {
					// 'Cancel' action
					public void run() {
						JOptionPane.showMessageDialog(null, "Nevermind.");
					}
				});
	}
}
