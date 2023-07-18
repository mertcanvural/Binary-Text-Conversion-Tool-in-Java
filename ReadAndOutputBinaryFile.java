class ReadAndOutputBinaryFile
{
	public static void main(String[] args)
	{
		if( args.length < 1 )
		{
			System.out.println("format: ReadAndWriteBinaryFilesMultipleBytesAtATime \"input file\"");
			System.exit(0);
		}
		
		try
		{
			java.io.FileInputStream input = new java.io.FileInputStream(args[0]);
			int currentByte;
			int byteCount = 0;
			while( (currentByte = input.read()) >= 0 )
			{
				byteCount = byteCount+1;
				String binaryString = Integer.toBinaryString(currentByte);
				while( binaryString.length() < 8 )
				{
					binaryString = "0"+binaryString;
				}
				System.out.println(binaryString + " " + ((char) currentByte));
			}
			System.out.println("byteCount = " + byteCount);
			input.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
	}
}
