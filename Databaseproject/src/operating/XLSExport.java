package operating;


import  java.io.FileOutputStream;


import  org.apache.poi.hssf.usermodel.HSSFCell;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author ������
 *
 * 2018��2��27��
 */
public class XLSExport{
	

    private String xlsFileName;
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private HSSFRow row;

    //���캯������Ҫ֪�����ɵ��ļ�����
    public XLSExport(String fileName)  {
        this.xlsFileName = fileName;
        this.workbook = new HSSFWorkbook();
        this.sheet = workbook.createSheet();
   } 
 
     //����xls�ļ�
     public void exportXLS() throws Exception{
        try{
           FileOutputStream fOut = new FileOutputStream(xlsFileName);
           workbook.write(fOut);
           fOut.flush();
           fOut.close();
       }catch(Exception e){
			e.printStackTrace();
			throw new Exception();
       }  
   } 
 
      //����һ��
      public void createRow(int index)  {
        this.row = this.sheet.createRow(index);
 } 
 
      //�����кź���������䵥Ԫ��
      public void setCell(int index, String value)  {
         HSSFCell cell = this.row.createCell((short)index);
         cell.setCellValue(value);
   } 

}
