; Конвертор всякого говна в русский текст.
; Автор Tirarex 2016г
fntArialB=LoadFont("Arial Cyr",18,True,False,False) 
SetFont fntArialB 
Type Xlib
	Field Id
	Field SourceChar$
	Field DestChar$
End Type
;Загружаем либу с переводом символов в текст. 
Function LoadLib()
	file = OpenFile("LIB\Testlib.txt") 
	NumOfChars = ReadLine$( file ) 
	For i=0 To NumOfChars
		xl.Xlib = New Xlib 
		xl\SourceChar$=ReadLine$( file )
		xl\DestChar$= ReadLine$( file ) 
		Print "Добавлена буква '"+xl\DestChar$+"' с кодом "+xl\SourceChar$
	Next
	CloseFile( file ) 
	Print "библиотека загружена"
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
		Print "перевел строку :"+rl$
		WriteLine( fileout, rl$) 
	Wend 
	CloseFile( file ) 
	CloseFile( fileout ) ;ExecFile("UTFCast.exe "+"OUTPUT\"+name$+ " "+"OUTPUT\"+name$+"utf8")
End Function
Print "Кручу педали"
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
			Print "Работаю с файлом:" + file$  + " из папки:"+Dirz$
			DecodeFile(file$,Dirz$ )
		End If 
	Forever 
	CloseDir myDir 
End Function
Print "Работа окончена"
;WaitKey

;~IDEal Editor Parameters:
;~F#4#A#16#28
;~C#Blitz3D