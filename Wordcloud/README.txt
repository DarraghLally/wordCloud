**Word Cloud**
2019 DSA project set by Dr. John Healy. The purpose of this program is to create a wordcloud 
image from a read in file or URL.


**User Guide**
(1) Run JAR
(2) Choose:
	(1) File Reading: Enter file with extension 
		eg; DeBelloGallico.txt (assuming file is in project folder)
	(2) URL Reading: Enter full URL 
		eg; http://www.gutenberg.org/files/2600/2600-h/2600-h.htm
	(3) Exit
(3) Enter the number of words wanted in image
	eg; 20
(4) Enter save file name
	eg; myImage.png

myImage.png will now appear in the project folder


**Features**
(1) Timer printing to console - starts when parse is called, ends when image is generated
(2) Max and Min word count printed to console
(3) Error handling for user input
(4) Fully commented code
(5) Big O notation break down for classes - block comment at top of classes

Both the user defined file/URL and the hardcoded ignorewords file are put into hash maps
They are compared against each other and words are placed into a queue depending on the frequency of 
	the allowed words
Then the n words are placed in the image, where n is set by the user


**Author**
Darragh Lally - G00220290

