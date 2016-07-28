; ��������� ������� ����� � ������� �����.
; ����� Tirarex 2016�
fntArialB=LoadFont("Arial Cyr",18,True,False,False) 
SetFont fntArialB 
Type Xlib
	Field Id
	Field SourceChar$
	Field DestChar$
End Type
;��������� ���� � ��������� �������� � �����. 
Function LoadLib()
	file = OpenFile("LIB\Testlib.txt") 
	NumOfChars = ReadLine$( file ) 
	For i=0 To NumOfChars
		xl.Xlib = New Xlib 
		xl\SourceChar$=ReadLine$( file )
		xl\DestChar$= ReadLine$( file ) 
		Print "��������� ����� '"+xl\DestChar$+"' � ����� "+xl\SourceChar$
	Next
	CloseFile( file ) 
	Print "���������� ���������"
End Function
Function DecodeFile(name$,dir$)
	file = ReadFile(dir$+name$)
	CopyFile "LIB\Source.patch", "OUTPUT\"+name$
	fileout = OpenFile("OUTPUT\"+name$) 
	While Not Eof(file) 
		rl$=ReadLine$(file)
		For xl.Xlib = Each Xlib
			rl$ =Replace(rl$,xl\SourceChar$,xl\DestChar$)
		Next
		Print "������� ������ :"+rl$
		WriteLine( fileout, rl$) 
	Wend 
	CloseFile( file ) 
	CloseFile( fileout ) ;ExecFile("UTFCast.exe "+"OUTPUT\"+name$+ " "+"OUTPUT\"+name$+"utf8")
End Function
Print "����� ������"
LoadLib()
ReadDirRecur("INPUT\")
Function ReadDirRecur(Dirz$)
	myDir=ReadDir(Dirz$)  
	file$=NextFile$(myDir) 
	file$=NextFile$(myDir) 
	Repeat 
		file$=NextFile$(myDir) 
		If file$="" Then Exit 
		If  Instr(file$,".patch")
			Print "������� � ������:" + file$  + " �� �����:"+Dirz$
			DecodeFile(file$,Dirz$ )
		End If 
	Forever 
	CloseDir myDir 
End Function
Print "������ ��������"
;WaitKey

;~IDEal Editor Parameters:
;~F#4#A#16#28
;~C#Blitz3D