package com.alja;

public class MainChallenge {

}


    /*

    Modify:

v    adding items to the shopping basket doesn't reduce the stock count but,
        reserves the requested number of items.

v        add a "reserved" field to the StockItem class to store the number of items reserved


v        should not be possible to reserve more than available in  stock of any item
        An item's available stock is the stock count less the reserved amount.


    CHECK OUT BASKET
v    The stock count for each item is reduced when a basket is checked out,
v       at which point all reserved items in the basket have their actual stock count reduced.

v    Once checkout is complete, the contents of the basket are cleared.



v    It should also be possible to "unreserve" items that were added to the basket
v       - prevent any attempt to unreserve more items than were reserved for a basket.


v    Add code to Main that will unreserve items after they have been added to the basket,
    as well as unreserving items that have not been added
    to make sure that the code can cope with invalid requests like that.



    After checking out the baskets, display the full stock list and the contents of each
    basket that you created.

     */