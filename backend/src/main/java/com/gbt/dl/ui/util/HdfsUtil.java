package com.gbt.dl.ui.util;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gbt.dl.ui.constants.HdfsProperties;

public class HdfsUtil {
	
	
	private static final Logger logger = LogManager.getLogger(HdfsUtil.class);
	private static final String GET_URL1 = "http://gtwtdlnnbdlp01.gbt.gbtad.com:50070/webhdfs/v1/user/svc_aegbtdi/?op=LISTSTATUS&user.name=svc_aegbtdi";
	private static final String GET_URL2 = "http://gtwtdlnnbdlp02.gbt.gbtad.com:50070/webhdfs/v1/user/svc_aegbtdi/?op=LISTSTATUS&user.name=svc_aegbtdi";

	private HdfsProperties hdfsProperties;

	public HdfsUtil(HdfsProperties hdfsProperties) {
		this.hdfsProperties = hdfsProperties;
	}

	public HdfsProperties getHdfsProperties() {
		return hdfsProperties;
	}

	public static void main(String[] args) throws IOException {
		HdfsUtil hdfsUtil = new HdfsUtil(null);
		hdfsUtil.uploadFile("", "");
	}
	
	
	
	public static int HdfsConnectionStatus(String hostname) {

		try {
			URL url = new URL(hostname);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			return con.getResponseCode();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	public void uploadFile(String... args) throws IOException {
		Configuration conf = new Configuration();

		// conf.setBoolean(CROSS_PLATFORM, true);
		// conf.setBoolean(CROSS_PLATFORM, true);
		logger.info("host1: " + hdfsProperties.getHost1());
		logger.info("host2: " + hdfsProperties.getHost2());
		logger.info("defaultfs: " + hdfsProperties.getDefaultfs());
		
		if (HdfsConnectionStatus(GET_URL1) == HttpURLConnection.HTTP_OK) {
			conf.set(hdfsProperties.getDefaultfs(), hdfsProperties.getHost1());
		} else if (HdfsConnectionStatus(GET_URL2) == HttpURLConnection.HTTP_OK) {
			conf.set(hdfsProperties.getDefaultfs(), hdfsProperties.getHost2());
		}

		GenericOptionsParser optionsParser = new GenericOptionsParser(conf, args);

		String[] remainingArgs = optionsParser.getRemainingArgs();
		if (remainingArgs.length < 2) {
			System.err.println("Usage: upload <source> <dest>");
			System.exit(2);
		}

		Path source = new Path(args[0]);
		Path dest = new Path(args[1]);

		logger.info("source::::: " + source.toUri().getPath());
		logger.info("dest:::: " + dest.toUri().getPath());
		FileSystem fs = FileSystem.get(conf);

		fs.copyFromLocalFile(true, true, source, dest);
	}
	 public String appendHdfsFile(String... args) throws IOException {

		    Configuration conf = new Configuration();
		    // conf.setBoolean(CROSS_PLATFORM, true);
		logger.info("host1: " + hdfsProperties.getHost1());
		logger.info("host2: " + hdfsProperties.getHost2());
		logger.info("defaultfs: " + hdfsProperties.getDefaultfs());
		
		if (HdfsConnectionStatus(GET_URL1) == HttpURLConnection.HTTP_OK) {
			conf.set(hdfsProperties.getDefaultfs(), hdfsProperties.getHost1());
		} else if (HdfsConnectionStatus(GET_URL2) == HttpURLConnection.HTTP_OK) {
			conf.set(hdfsProperties.getDefaultfs(), hdfsProperties.getHost2());
		}

		    GenericOptionsParser optionsParser = new GenericOptionsParser(conf,
		        args);
		    String[] remainingArgs = optionsParser.getRemainingArgs();
		    if (remainingArgs.length < 2) {
		      System.err.println("Usage: upload <source> <dest>");
		      System.exit(2);
		    }
		    try(FileSystem fileSystem = FileSystem.get(conf))
		    {
		    Path hdfsPath = new Path(args[1]);
		    String content = args[0];
		    FSDataOutputStream fileOutputStream = null;
		    
		      if (fileSystem.exists(hdfsPath)) {
		        System.out.println("file is exist");
		        System.out.println("hdfsPath"+hdfsPath);
		        fileOutputStream = fileSystem.append(hdfsPath);
		        fileOutputStream.writeBytes(content);
		      } else {
		        System.out.println("file is not exist");
		        fileOutputStream = fileSystem.create(hdfsPath);
		        fileOutputStream.writeBytes(content);
		      }
		    }catch(IOException e)
		    {
		    	e.printStackTrace();
		    	throw e;
		    }
		    /*finally {
		      if (fileSystem != null) {
		        fileSystem.close();
		      }
		      if (fileOutputStream != null) {
		        fileOutputStream.close();
		      }
		    }*/

		    return "success";

		  }


}
