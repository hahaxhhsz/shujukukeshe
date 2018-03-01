package operating;


import  java.io.FileOutputStream;


import  org.apache.poi.hssf.usermodel.HSSFCell;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author 王晨超
 *
 * 2018年2月27日
 */
public class XLSExport{
	

    private String xlsFileName;
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private HSSFRow row;

    //构造函数，需要知道生成的文件的名
    public XLSExport(String fileName)  {
        this.xlsFileName = fileName;
        this.workbook = new HSSFWorkbook();
        this.sheet = workbook.createSheet();
   } 
 
     //导出xls文件
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
 
      //增加一行
      public void createRow(int index)  {
        this.row = this.sheet.createRow(index);
 } 
 
      //根据列号和内容来填充单元格
      public void setCell(int index, String value)  {
         HSSFCell cell = this.row.createCell((short)index);
         cell.setCellValue(value);
   } 

}
