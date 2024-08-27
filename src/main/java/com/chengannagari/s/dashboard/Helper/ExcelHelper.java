package com.chengannagari.s.dashboard.Helper;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.chengannagari.s.dashboard.Entity.Role;
import com.chengannagari.s.dashboard.Entity.User;

public class ExcelHelper {

    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<User> ConvertExcelToListOfEmployee(InputStream is) {
        List<User> userList = new ArrayList<>();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("data");

            Iterator<Row> rowIterator = sheet.iterator();

         
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.iterator();
                User user = new User();

                int cid = 0;

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    switch (cid) {
                        case 0:
                            user.setFirstName(getStringValue(cell));
                            break;
                        case 1:
                            user.setLastName(getStringValue(cell));
                            break;
                        case 2:
                            user.setUserName(getStringValue(cell));
                            break;
                        case 3:
                            user.setGender(getStringValue(cell));
                            break;
                        case 4:
                           
                            if (cell.getCellType() == CellType.NUMERIC) {
                                user.setDateOfBirth(cell.getDateCellValue());
                            } else {
                                
                            }
                            break;
                        case 5:
                            user.setCountry(getStringValue(cell));
                            break;
                        case 6:
                            user.setState(getStringValue(cell));
                            break;
                        case 7:
                            user.setDistrict(getStringValue(cell));
                            break;
                        case 8:
                            user.setAddress(getStringValue(cell));
                            break;
                        case 9:
                            // Handle Phone Number
                            if (cell.getCellType() == CellType.NUMERIC) {
                                user.setPhoneNumber((long) cell.getNumericCellValue());
                            } else if (cell.getCellType() == CellType.STRING) {
                                user.setPhoneNumber(Long.parseLong(cell.getStringCellValue()));
                            }
                            break;
                        case 10:
                            user.setEmail(getStringValue(cell));
                            break;
                        case 11:
                            // Encrypt password
//                            String rawPassword = getStringValue(cell);
//                            String encryptedPassword = passwordEncoder.encode(rawPassword);
//                            user.setPassword(encryptedPassword);
                        	user.setPassword(getStringValue(cell));
                            break;
                            
                            
                        case 12:
                            user.setImage(getStringValue(cell));
                            break;
                        case 13: // Assuming role is at index 13
                            String roleString = getStringValue(cell);
                            if (roleString != null && !roleString.isEmpty()) {
                                Role role;
                                if (roleString.equalsIgnoreCase("admin")) {
                                    role = Role.ADMIN;
                                } else if (roleString.equalsIgnoreCase("user")) {
                                    role = Role.USER;
                                } else {
                                    // Handle invalid role values
                                    // You can log an error or throw an exception here
                                    // For now, let's set the role to a default value
                                    role = Role.USER; // or whatever default role you want to set
                                }
                                user.setRole(role);
                            }
                            break;
                        default:
                            break;
                    }

                    cid++;
                }

                userList.add(user);
            }

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

    private static String getStringValue(Cell cell) {
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return "";
        }
    }
}
