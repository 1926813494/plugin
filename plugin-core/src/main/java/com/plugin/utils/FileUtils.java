package com.plugin.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileUtils {

	 /**
     * The number of bytes in a kilobyte.
     */
    public static final long ONE_KB = 1024;

    /**
     * The number of bytes in a megabyte.
     */
    public static final long ONE_MB = ONE_KB * ONE_KB;

    /**
     * The file copy buffer size (30 MB)
     */
    private static final long FILE_COPY_BUFFER_SIZE = ONE_MB * 30;
	
    
    
    
	public static void copyFileToDirectory(File srcFile, File destDir) throws IOException {
		copyFileToDirectory(srcFile, destDir, true);
	}

	public static void copyFileToDirectory(File srcFile, File destDir, boolean preserveFileDate) throws IOException {
		if (destDir == null) {
			throw new NullPointerException("Destination must not be null");
		}
		if (destDir.exists() && destDir.isDirectory() == false) {
			throw new IllegalArgumentException("Destination '" + destDir + "' is not a directory");
		}
		File destFile = new File(destDir, srcFile.getName());
		copyFile(srcFile, destFile, preserveFileDate);
	}

	public static void copyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
		if (srcFile == null) {
			throw new NullPointerException("Source must not be null");
		}
		if (destFile == null) {
			throw new NullPointerException("Destination must not be null");
		}
		if (srcFile.exists() == false) {
			throw new FileNotFoundException("Source '" + srcFile + "' does not exist");
		}
		if (srcFile.isDirectory()) {
			throw new IOException("Source '" + srcFile + "' exists but is a directory");
		}
		if (srcFile.getCanonicalPath().equals(destFile.getCanonicalPath())) {
			throw new IOException("Source '" + srcFile + "' and destination '" + destFile + "' are the same");
		}
		File parentFile = destFile.getParentFile();
		if (parentFile != null) {
			if (!parentFile.mkdirs() && !parentFile.isDirectory()) {
				throw new IOException("Destination '" + parentFile + "' directory cannot be created");
			}
		}
		if (destFile.exists() && destFile.canWrite() == false) {
			throw new IOException("Destination '" + destFile + "' exists but is read-only");
		}
		doCopyFile(srcFile, destFile, preserveFileDate);
	}

	private static void doCopyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
		if (destFile.exists() && destFile.isDirectory()) {
			throw new IOException("Destination '" + destFile + "' exists but is a directory");
		}

		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel input = null;
		FileChannel output = null;
		try {
			fis = new FileInputStream(srcFile);
			fos = new FileOutputStream(destFile);
			input = fis.getChannel();
			output = fos.getChannel();
			long size = input.size();
			long pos = 0;
			long count = 0;
			while (pos < size) {
				count = (size - pos) > FILE_COPY_BUFFER_SIZE ? FILE_COPY_BUFFER_SIZE : (size - pos);
				pos += output.transferFrom(input, pos, count);
			}
		} finally {
			IOUtils.closeQuietly(output);
			IOUtils.closeQuietly(fos);
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(fis);
		}

		if (srcFile.length() != destFile.length()) {
			throw new IOException("Failed to copy full contents from '" + srcFile + "' to '" + destFile + "'");
		}
		if (preserveFileDate) {
			destFile.setLastModified(srcFile.lastModified());
		}
	}

	
	
}
