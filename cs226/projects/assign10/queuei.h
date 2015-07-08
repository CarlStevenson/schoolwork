// Header file provided by Dr. Bracken.
// ********************************************************
// Header file QueueP.h for the ADT queue.
// Pointer-based implementation.
// ********************************************************
using namespace std;
typedef int queueItemType;
struct queueNode;  // defined in implementation file
typedef queueNode* ptrType;  // pointer to node
class queueClass
{
public:
// constructors and destructor:
   queueClass();                     // default constructor
   ~queueClass();                    // destructor

// queue operations:
   bool QueueIsEmpty() const;
   // Determines whether a queue is empty.
   // Precondition: None.
   // Postcondition: Returns true if the queue is empty;
   // otherwise returns false.
   void QueueInsert(queueItemType NewItem, bool& Success);
   // Inserts an item at the back of a queue.
   // Precondition: NewItem is the item to be inserted. 
   // Postcondition: If insertion was successful, NewItem
   // is at the back of the queue and Success is true; 
   // otherwise Success is false.
   void QueueDelete(bool& Success);
   // Deletes the front of a queue.
   // Precondition: None.
   // Postcondition: If the queue was not empty, the item 
   // that was added to the queue earliest is deleted and 
   // Success is true. However, if the queue was empty, 
   // deletion is impossible and Success is false.
   void QueueDelete(queueItemType& QueueFront, 
                    bool& Success);
   // Retrieves and deletes the front of a queue.
   // Precondition: None.
   // Postcondition: If the queue was not empty, QueueFront 
   // contains the item that was added to the queue 
   // earliest, the item is deleted, and Success is true. 
   // However, if the queue was empty, deletion is 
   // impossible and Success is false.
   void GetQueueFront(queueItemType& QueueFront, 
                      bool& Success) const;
   // Retrieves the item at the front of a queue.
   // Precondition: None.
   // Postcondition: If the queue was not empty, QueueFront 
   // contains the item that was added to the queue earliest 
   // and Success is true. However, if the queue was empty, 
   // the operation fails, QueueFront is unchanged, and 
   // Success is false. The queue is unchanged.

   void QueuePrint() const;
   int NumberOfQueueElements() const;
private:
//BackPtr points to the newest data enqued
   ptrType BackPtr;
   int numberofqueueelements;
};  // end class
// End of header file.
