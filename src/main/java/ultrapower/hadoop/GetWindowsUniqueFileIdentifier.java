package ultrapower.hadoop;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinBase.FILETIME;

public class GetWindowsUniqueFileIdentifier {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// http://msdn.microsoft.com/en-us/library/windows/desktop/aa363858%28v=vs.85%29.aspx
		String path = "E:\\data\\logs\\NGINX_ACCESS.2017-12-28.log";
		final int FILE_SHARE_READ = (0x00000001);
		final int OPEN_EXISTING = (3);
		final int GENERIC_READ = (0x80000000);
		final int FILE_ATTRIBUTE_ARCHIVE = (0x20);
		WinBase.SECURITY_ATTRIBUTES attr = null;
		ultrapower.hadoop.Kernel32.BY_HANDLE_FILE_INFORMATION lpFileInformation = new ultrapower.hadoop.Kernel32.BY_HANDLE_FILE_INFORMATION();
		com.sun.jna.platform.win32.WinNT.HANDLE hFile = null;
		hFile = Kernel32.INSTANCE.CreateFile(path, GENERIC_READ, FILE_SHARE_READ, attr, OPEN_EXISTING, FILE_ATTRIBUTE_ARCHIVE, null);
		boolean returnVal = ultrapower.hadoop.Kernel32.INSTANCE.GetFileInformationByHandle(hFile, lpFileInformation);
		hFile = null;
		if (returnVal && lpFileInformation != null) {
			System.out.println("file_creation_time=" + FILETIME.filetimeToDate(lpFileInformation.ftCreationTime.dwHighDateTime, lpFileInformation.ftCreationTime.dwLowDateTime).toLocaleString());
			System.out.println("file_unique_index=" + Integer.toHexString(lpFileInformation.dwVolumeSerialNumber.intValue()) + "-" + lpFileInformation.nFileIndexLow + "-" +lpFileInformation.nFileIndexHigh);
		}
	}

}
