package ultrapower.hadoop;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.zookeeper.common.IOUtils;

public class URLCat {
	
	static {
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
	}

	public static void main(String[] args) {
		InputStream in = null;
		try {
			try {
				in = new URL("hdfs://10.0.0.160:9000/chengxj/in/1901").openStream();
				IOUtils.copyBytes(in, System.out, 4096, false);
			} catch (MalformedURLException e) {
				
			} catch (IOException e) {
				
			}
		} finally {
			IOUtils.closeStream(in);
		}

	}

}
