FR:

    1. one vendor can add maximum of n products 
    2. one user cxan bid maximum of 2 bids
    3. getAllBids() should return all the ongoing bids
    4. show all the bids which user has placed in the past (productid, bidAmout, status={WON, LOST})
    5. show all the biddings vendor has listed for bidding (productid, basePrice, soldPrice, profit)
    6. register user and vendor
    7. add biditem by vendor
    8. start bid by vendor
    9. end bid by vendor
    10. list bid items by user
    11. bid by user

Entities: 

    1. BaseUser -> {User, Vendor} -> (id, phNo, email)
    2. Product (id, baseAmt, isAvailableForSold, vendorId)


