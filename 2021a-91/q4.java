//a.

public synchronized int service(){
    //...;
        while(freeTellers.size() == 0 ){
            try{wait();}
            catch(InterruptedException e){
                System.out.print(e);
            }
        }
        teller = freeTellers.get(0);
        freTellers.remove(0);
        return teller;
}

public synchronized void release(int teller){
    freeTellers.add(teller);
    notifyAll();
}


//b.
public synchronized int service(int tellerID) {
    while (!freeTellers.contains(tellerID)) {
        try {
            wait();
        } catch (InterruptedException e) {
            System.out.print(e);
        }
    }
    freeTellers.remove(Integer.valueOf(tellerID));
    return tellerID;
}

public synchronized void release(int teller) {
    freeTellers.add(Integer.valueOf(teller));
    notifyAll();
}
