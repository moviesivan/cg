from tkinter import *

def frange(start, stop, step):
     i = start
     while i < stop:
         yield i
         i += step

# a subclass of Canvas for dealing with resizing of windows
class ResizingCanvas(Canvas):
    def __init__(self,parent,**kwargs):
        Canvas.__init__(self,parent,**kwargs)
        self.bind("<Configure>", self.on_resize)
        self.height = self.winfo_reqheight()
        self.width = self.winfo_reqwidth()

    def on_resize(self,event):
        # determine the ratio of old width/height to new width/height
        wscale = float(event.width)/self.width
        hscale = float(event.height)/self.height
        self.width = event.width
        self.height = event.height
        # resize the canvas 
        self.config(width=self.width, height=self.height)
        # rescale all the objects tagged with the "all" tag
        self.scale("all",0,0,wscale,hscale)

def redraw(entries):
    if (float)(entries[0].get()) > 0:
        a = (float)(entries[0].get())
    else :
        a = 1
        entries[0].delete(0,END)
        entries[0].insert(0,"1")
    if (float)(entries[1].get()) < -1:
        A = (float)(entries[1].get())
    else :
        A = -2
        entries[1].delete(0,END)
        entries[1].insert(0,"-2")
    if (float)(entries[2].get()) < -1:
        B = (float)(entries[2].get())
    else :
        B = -2
        entries[2].delete(0,END)
        entries[2].insert(0,"-2")
    i=0
    X = list()
    Y = list()
    for t in frange(A,B,0.1):
        x = (float)(3)*a*t/(1+t**3)
        y = (float)(3)*a*t**2/(1+t**3)
        X+=[x]
        Y+=[y]
        i+=1
    entries[3].delete("all")
    for ind in range(1,i):
        entries[3].create_line(X[ind-1]*50,Y[ind-1]*(-50),X[ind]*50,Y[ind]*(-50))
    

def main():
    root = Tk()
    frame1 = Frame(root)
    frame1.pack(fill=BOTH, expand=YES)
    L1 = Label(frame1, text="a")
    L1.pack( side = LEFT)
    E1 = Entry(frame1)
    E1.insert(END, "1")
    E1.pack(side = LEFT)
    L2 = Label(frame1, text="A")
    L2.pack( side = LEFT)
    E2 = Entry(frame1)
    E2.insert(END, "-2")
    E2.pack(side = LEFT)
    L3 = Label(frame1, text="B")
    L3.pack( side = LEFT)
    E3 = Entry(frame1)
    E3.insert(END, "-2")
    E3.pack(side = LEFT)
    frame2 = Frame(root)
    frame2.pack(fill=BOTH, expand=YES)
    mycanvas = ResizingCanvas(frame2,width=500, height=500, bg="white", highlightthickness=0)
    mycanvas.pack( fill=BOTH, expand=YES)
    ents = (E1,E2,E3, mycanvas)
    b = Button(frame1, text="Update", command=(lambda e=ents: redraw(e)))
    b.pack(side = LEFT)

    # add some widgets to the canvas

    # tag all of the drawn widgets
    mycanvas.addtag_all("all")
    root.mainloop()

if __name__ == '__main__':
    main() 
