package ultrapower.hadoop;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.zookeeper.common.IOUtils;

public class FileSystemCat {

	public static void main(String[] args) {
		String uri = "hdfs://10.0.0.160:9000/chengxj/in/1901";
		Configuration conf = new Configuration();
		FileSystem fs = null;
		InputStream in = null;
		try {
			fs = FileSystem.get(URI.create(uri), conf);
			in = fs.open(new Path(uri));
			IOUtils.copyBytes(in, System.out, 4096, false);
		} catch (IOException e) {
		
		} finally {
			IOUtils.closeStream(in);
		}
	}

}
