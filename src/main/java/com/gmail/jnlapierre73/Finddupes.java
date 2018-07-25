package com.gmail.jnlapierre73;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.gmail.jnlapierre73.entities.Contact;

public class Finddupes {
    
	private static final String NORMAL = "csv/normal.csv";
	private static final String ADVANCED = "csv/advanced.csv";
	
	private static final String[] FILE_HEADER_MAPPING = { "id", "first_name", "last_name", "company", "email",
			"address1", "address2", "zip", "city", "state_long", "state", "phone" };
	// Contact attributes
	private static final String HIDDEN_ID = "id";
	private static final String FIRST_NAME = "first_name";
	private static final String LAST_NAME = "last_name";
	private static final String COMPANY = "company";
	private static final String EMAIL = "email";
	private static final String ADDRESS1 = "address1";
	private static final String ADDRESS2 = "address2";
	private static final String ZIP = "zip";
	private static final String CITY = "city";
	private static final String STATE_LONG = "state_long";
	private static final String STATE = "state";
	private static final String PHONE = "phone";

	private static ArrayList<Contact> sourceContacts;
	private static SortedMap<Integer, ArrayList<Contact>> potentialDuplicates;
	private static ArrayList<Contact> notDuplicated;

	public static void main(String[] args) {
        //Create a new list of contacts to be filled by CSV file data 
        List<Contact> sourceContacts = new ArrayList<Contact>();
        // Create a new list of not duplicated contacts
        List<Contact> notDuplicated = new ArrayList<Contact>();
        // Create a new list of grouped potential duplicates
        SortedMap<Integer, ArrayList<Contact>> potentialDuplicates = new TreeMap<Integer, ArrayList<Contact>>();

		// Load the file into an initial array list of contacts
		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		// Create the CSVFormat object with the header mapping
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);
		
		//Get file from resources folder
		ClassLoader classLoader = Finddupes.class.getClassLoader();
		File file = new File(classLoader.getResource(NORMAL).getFile());
		
        try {
            //initialize FileReader object
            fileReader = new FileReader(file);
            //initialize CSVParser object
            csvFileParser = new CSVParser(fileReader, csvFileFormat);
            //Get a list of CSV file records
            List<CSVRecord> csvRecords = csvFileParser.getRecords(); 
            //Read the CSV file records starting from the second record to skip the header
            for (int i = 1; i < csvRecords.size(); i++) {
                CSVRecord record = csvRecords.get(i);
                //Create a new student object and fill his data
                Contact contact = new Contact();
                contact.setId(i);  // Give each row a unique id, in this case based on line position
                contact.setHiddenId(record.get(HIDDEN_ID));
                contact.setFirstName(record.get(FIRST_NAME));
                contact.setLastName(record.get(LAST_NAME));
                contact.setCompany(record.get(COMPANY));
                contact.setEmail(record.get(EMAIL));
                contact.setAddress1(record.get(ADDRESS1));
                contact.setAddress2(record.get(ADDRESS2));
                contact.setZip(record.get(ZIP));
                contact.setCity(record.get(CITY));
                contact.setStateLong(record.get(STATE_LONG));
                contact.setState(record.get(STATE));
                contact.setPhone(record.get(PHONE));
                
                sourceContacts.add(contact);  
            }
            //Print the contact list - Used for initial load and debugging
            //for (Contact contact : sourceContacts) {
            //    System.out.println(contact.toString());
            //}
        } 
        catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                csvFileParser.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader/csvFileParser !!!");
                e.printStackTrace();
            }
        }
        
        boolean duplicate = false;
        for (Contact initialContact : sourceContacts) {
        	duplicate = false;
        	for (Contact contact : sourceContacts) {
        		if (initialContact.getId() != contact.getId()) { // Do not get a false possitive duplication to itself
        			// Check to see if contact is a potential duplicate
        			if (initialContact.isDuplicate(contact)) {
        				// If so, check to see if we've already found and created a record for this duplicate
        				if (potentialDuplicates.containsKey(initialContact.getId())) {
        					potentialDuplicates.get(initialContact.getId()).add(contact);
        				} else {
        					// If not, create a new record and add both the initial contact and the duplicate
        					potentialDuplicates.put(initialContact.getId(), new ArrayList<Contact>());
        					potentialDuplicates.get(initialContact.getId()).add(initialContact);
        					potentialDuplicates.get(initialContact.getId()).add(contact);
        				}
        				// Flag it
        				duplicate = true;
        			}
        		}	
        	}

        	if (!duplicate) {
        		notDuplicated.add(initialContact);
        	}
        }
        
        System.out.println("Potential Duplicates: " + potentialDuplicates);
        System.out.println("Not duplictes: " + notDuplicated);
    }
		
}

