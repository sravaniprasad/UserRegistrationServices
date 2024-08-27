package com.chengannagari.s.dashboard.Helper;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.chengannagari.s.dashboard.Entity.User;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

public class PdfExporter {
private List<User> listUsers;


public PdfExporter(List<User> listUsers) {
	// TODO Auto-generated constructor stub
	this.listUsers=listUsers;
}

public void writeTableHeader(PdfPTable table) {
	
	PdfPCell cell=new PdfPCell();
	cell.setBackgroundColor(Color.LIGHT_GRAY);
	cell.setPadding(5);
	cell.setPaddingRight(5);
	//cell.setFixedWidth(100);
	
	Font font=FontFactory.getFont(FontFactory.HELVETICA);
	font.setColor(Color.WHITE);
	cell.setPhrase(new Phrase("UserId"));
	table.addCell(cell);
	
	cell.setPhrase(new Phrase("FirstName"));
	table.addCell(cell);
	
	cell.setPhrase(new Phrase("LastName"));
	table.addCell(cell);
	
	cell.setPhrase(new Phrase("UserName"));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Gender"));
	table.addCell(cell);
	
	cell.setPhrase(new Phrase("D.O.B"));
	table.addCell(cell);
	
	cell.setPhrase(new Phrase("Country"));
	table.addCell(cell);
	cell.setPhrase(new Phrase("State"));
	table.addCell(cell);
	cell.setPhrase(new Phrase("District"));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Address"));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Phonenumber"));
	table.addCell(cell);
	cell.setPhrase(new Phrase("E-Mail"));
	table.addCell(cell);
//	cell.setPhrase(new Phrase("password"));
//	table.addCell(cell);
//	cell.setPhrase(new Phrase("image"));
//	table.addCell(cell);

}
public void writeTableData(PdfPTable table) throws BadElementException, IOException {
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Define your desired date format

    for (User u : listUsers) {
        table.addCell(String.valueOf(u.getUserId()));
        table.addCell(String.valueOf(u.getFirstName()));
        table.addCell(String.valueOf(u.getLastName()));
        table.addCell(String.valueOf(u.getUsername()));
        table.addCell(String.valueOf(u.getGender()));

       
        String formattedDateOfBirth = dateFormat.format(u.getDateOfBirth());
        table.addCell(formattedDateOfBirth);

        table.addCell(String.valueOf(u.getCountry()));
        table.addCell(String.valueOf(u.getState()));
        table.addCell(String.valueOf(u.getDistrict()));
        table.addCell(String.valueOf(u.getAddress()));
        table.addCell(String.valueOf(u.getPhoneNumber()));
        table.addCell(String.valueOf(u.getEmail()));
    }
}


public void export(HttpServletResponse response) throws DocumentException, IOException   {
	Document document=new Document(PageSize.A4.rotate());
	PdfWriter.getInstance(document, response.getOutputStream());
	document.open();
	
	Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	font.setColor(Color.GREEN);
	font.setSize(20);
	Paragraph title=new Paragraph("List of all Student Results",font);
	title.setAlignment(Paragraph.ALIGN_CENTER);
	document.add(title);
	//document.add(new Paragraph("List of all Students"));
	PdfPTable table=new PdfPTable(12);
	table.setWidthPercentage(110);
	table.setSpacingBefore(29);
	//table.setHeaderRows(6);cell.setFixedWidth(100); // Set a fixed width for the cell
	
	//table.setWidths(new float[] {1.5f,3.5f,3.0f,3.0f,1.5f,3.0f,3.0f});
	  float[] columnWidths = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1.5f, 1}; // Equal width for each column
	    table.setWidths(columnWidths);
	writeTableHeader(table);
	writeTableData(table);

	document.add(table);
	document.close();
}
}
