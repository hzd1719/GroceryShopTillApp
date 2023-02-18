This an Application CLI written in java. The application promts you to enter a number of products by your choice. Then for every product you should enter the produc's name and price. There is an option for the admin 
to set a special deal item for the store - every second special item the customer buys has a price reduction by 50%. Moreover, the admin can set a two for three deal - if the customer buys all three items, from the first three scanned
items of that type, the chepeast will be free. The application assumes, customer choices to buy goods are promted elsewhere, therefore for testing purposes anything a customer buys is hardcoded. The application is made just for showing
some of the developer's knowledge, so it is chosen to make some of the program simplier, when more logic wouldn't show much of the developer's skills but would unnecessary extra time(for example, the goods don't have quantity, 
there is a possibility for more abstraction, but it would't contribute much to the app, and the UI is pretty simple and unrealistic). However, the application meets all the requirements and works properly.



## "Business" Requirements

- Create a basic Groceries Shop till which can `scan` fruits and vegetables of different types, producing a numeric result/bill in the end. Assume the currency is called `aws` and there is `100 cloud ('c' for short)` in 1 aws
- Apart from simply adding the value of each product, the till should contain logic for the following special deals:
  - `2 for 3` - for a given selection of items (customer buys 3 items but only pays for the value of 2 of them, the cheapest one is free). In case there are more than 3 items that are included in the `2 for 3` deal, the first 3 items are included.
    Example Deal ["banana", "orange", "tomato"], example items scanned ["banana", "orange", "orange", "tomato"] - the tomato is not included in the discount (the cheaper of `banana` or `orange` will be subtracted)
  - `buy 1 get 1 half price` - for a given selection of items (if the customer buys a given product under such offer, they receive a 50% reduction in the price of a second item of the same type)
- The till should be `programmable` so that whoever runs it can define 2 inputs:
  - The list of items supported by the till - each item with a given "price", "name"
  - Once a new item is added to the till, the administrator should be able to add it to any of the 2 special deals defined above
  - You should be able to scan a list of items and see the end price (any special deal discounts should be subtracted)

### Example:

Input groceries:

| Product | Price |
| ------- | :---: |
| apple   |  50c  |
| banana  |  40c  |
| tomato  |  30c  |
| potato  |  26c  |

Input deals:

- `2 for 3` - ["apple", "banana", "tomato"]
- `buy 1 get 1 half price` - potato

Example scanned customer basket: "apple", "banana", "banana", "potato", "tomato", "banana", "potato"

Expected Output: `1 aws and 99 clouds`
Explanation:

The items are processed(Scanned) in order:

- "apple", "banana", "banana" are picked up for the `2 for 3` deal and 1 of the bananas is free - total cost `90c`

- "potato", "tomato", "banana", "potato" are left. There is a `buy 1 get 1 half price` for potatoes, meaning the second potato will be half price (13c) so it will be `39c` for both

- The other 2 items scanned `tomato` and `banana` are not part of any deals so they are charged their basic price `70c` total

The total amount the is equal to: `90c + 39c + 70c = 1 aws and 99 clouds` (199 clouds = 1.99 aws)
