package com.helpers;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

public class IMAPHelper {

	Folder inbox;
	Store store;
	Message messages[];

	/**
	 * Connect to IMAP account
	 * 
	 * @param host
	 * @param username
	 * @param password
	 * @throws MessagingException
	 */
	public void connectToIMAP(String host, String username, String password) throws MessagingException {
		// Set the mail properties
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");

		// Create the session and get the store for read the mail.
		Session session = Session.getDefaultInstance(props, null);
		store = session.getStore("imaps");
		store.connect(host, username, password);
	}

	/**
	 * Get all unread email in a list
	 * 
	 * @return the number of unread messages
	 * @throws MessagingException
	 */
	public int getUnreadEmails() throws MessagingException {

		// Mention the folder name which you want to read.
		inbox = store.getFolder("Inbox");
		System.out.println("No of Unread Messages : " + inbox.getUnreadMessageCount());

		// Open the inbox using store.
		inbox.open(Folder.READ_WRITE);

		// Get the messages which is unread in the Inbox
		messages = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));

		// Use a suitable FetchProfile
		FetchProfile fp = new FetchProfile();
		fp.add(FetchProfile.Item.ENVELOPE);
		fp.add(FetchProfile.Item.CONTENT_INFO);
		inbox.fetch(messages, fp);

		// Return number of unread messages
		return messages.length;
	}

	/**
	 * Get email subject
	 * 
	 * @param indexOfEmail
	 * @return the email subject
	 * @throws MessagingException
	 */
	public String getEmailSubject(int indexOfEmail) throws MessagingException {
		String subject = messages[indexOfEmail].getSubject();
		System.out.println("SUBJECT: " + subject);
		return subject;
	}

	/**
	 * Get email sender
	 * 
	 * @param indexOfEmail
	 * @return string email FROM
	 * @throws MessagingException
	 */
	public String getEmailFrom(int indexOfEmail) throws MessagingException {
		Address[] a;
		String fromAddresses = "";
		if ((a = messages[indexOfEmail].getFrom()) != null) {
			for (int j = 0; j < a.length; j++) {
				System.out.println("FROM: " + a[j].toString());
				fromAddresses += a[j].toString();
			}
		}
		return fromAddresses;
	}

	/**
	 * Get email recipients
	 * 
	 * @param indexOfEmail
	 * @return String email TO
	 * @throws MessagingException
	 */
	public String getEmailRecipients(int indexOfEmail) throws MessagingException {
		Address[] a;
		String toAddresses = "";
		if ((a = messages[indexOfEmail].getRecipients(Message.RecipientType.TO)) != null) {
			for (int j = 0; j < a.length; j++) {
				System.out.println("TO: " + a[j].toString());
				toAddresses += a[j].toString();
			}
		}
		return toAddresses;
	}

	/**
	 * Get email body from index
	 * 
	 * @param indexOfEmail
	 * @return String email body
	 * @throws IOException
	 * @throws MessagingException
	 */
	public String getEmailBody(int indexOfEmail) throws MessagingException, IOException {

		String textContent = "";
		if (messages[indexOfEmail].getContentType().startsWith("multipart")) {
			MimeMultipart content = (MimeMultipart) messages[indexOfEmail].getContent();
			for (int i = 0; i < content.getCount(); i++) {
				BodyPart part = content.getBodyPart(i);
				textContent += part.getContent();
			}
		} else {
			textContent += messages[indexOfEmail].getContent().toString();
		}
		System.out.println("BODY: " + textContent.toString());
		return textContent.toString();
	}

	/**
	 * Used to test the helper. Print all unread emails from account
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {

		IMAPHelper iMAPHelper = new IMAPHelper();

		iMAPHelper.connectToIMAP("imap.gmail.com", "scoalainformalait@gmail.com", "1nf0rmala");

		int unreadEmails = iMAPHelper.getUnreadEmails();

		for (int i = 0; i < unreadEmails; i++) {
			iMAPHelper.getEmailSubject(i);
			iMAPHelper.getEmailFrom(i);
			iMAPHelper.getEmailRecipients(i);
			iMAPHelper.getEmailBody(i);
		}

	}

}
