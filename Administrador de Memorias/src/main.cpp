#include <iostream>

using namespace std;

void printProcesses();
//Prints the info of the processes running, currently only prints the number of processes running

void printRAM();
//Prints the information of the RAM

void newProcess();
//Enters a new process on RAM

void mostrar();
//muestra el estado de la memoria ram

void cls();
//Agrega varias lineas para parecer que borra la pantalla


//Declaring variables
    static int memory_size = 0;
    static int available_memory = 0;
    static const int SO_MEMORY = 50;
    static int exeProcesses = 0;
    static int total_Proc = 0;

int main()
{

    cls();
    cout << "Manage Memory" << endl;
    cout << "Memory size = ";
    cin >> memory_size;
    while(memory_size < SO_MEMORY){
        cerr << "The OS cannot work on such little memory";
        cout << endl << "Memory size = ";
        cin >> memory_size;
    }
    available_memory = memory_size - SO_MEMORY;
    cls();
    cout << "Memory size = " << memory_size<< endl;
    cout << "New process ? y/n : ";
    char op;
    cin >> op;
    if((op == 'y') || (op == 'Y')){
        newProcess();
    }

    printRAM();

    return 0;
}

void newProcess(){
        total_Proc++;
         int process_size;
        cout << "Process memory size? : " ;
        cin >> process_size;
        if (process_size < available_memory){
            available_memory -= process_size;
            exeProcesses++;
        }else{
            cout << "Insuficient memory for the process";
        }
}

void printRAM(){
        cout << "Memory Size: " << memory_size << endl;
        cout << "Available memory:  " << available_memory<<endl;
        printProcesses();
}

void printProcesses(){
    cout << "Executing Processes: " <<  exeProcesses << endl;
    cout << "Processes Waiting: " << total_Proc-exeProcesses << endl;

}

void cls(){
        for(int i = 0; i < 30;i++)
        cout << endl;
}
