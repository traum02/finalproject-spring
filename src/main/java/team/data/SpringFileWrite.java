package team.data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class SpringFileWrite {

	private FileOutputStream fos;
	
	public void writeFile(MultipartFile file,String path)
	{
		//���� ��
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		String fileName = file.getOriginalFilename(); //�ö� ���ϰ� �̸��� ����
		
		
	
		try {
			byte []fileData = file.getBytes();
			fos = new FileOutputStream(path + "\\" + fileName);
			fos.write(fileData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}//��
}
