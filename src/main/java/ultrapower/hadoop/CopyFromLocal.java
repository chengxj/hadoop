package ultrapower.hadoop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class CopyFromLocal {

	public static void main(String[] args) {
		String local = "E:/data/logs/1902";
		String uri = "hdfs://10.0.0.160:9000/chengxj/in/1902";
		FileInputStream in = null;
		OutputStream out = null;
		Configuration conf = new Configuration();
		try {
			in = new FileInputStream(new File(local));
			FileSystem fs = FileSystem.get(URI.create(uri), conf);
			out = fs.create(new Path(uri), new Progressable() {
				
				public void progress() {
					System.out.print("*");
				}
				
			});
			IOUtils.copyBytes(in, out, conf);
		} catch(IOException e) {
			System.out.println(e.getMessage());
		} finally {
			IOUtils.closeStream(in);
			IOUtils.closeStream(out);
		}
	}

}
