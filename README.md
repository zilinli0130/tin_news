# tinnews_project

### *Introduction*


---

### *Developer Environment*

---

### *Software Architecture*
---

### *Software Fragment Sub-architecture*

#### 1. Home Fragment

The home fragment is the component for users to interact with news card. User can swipe the news card to left side to unlike it or swipe the cards to right side to like it. The other way to achieve the like/dislike actions is to click the like/dislike buttons located on the bottom of home tab. 

The `HomeFragment` consists of two main instances: `HomeViewModel` and `HomeFragmentBinding`. The `HomeViewModel` is an instance for querying the news data through extrernal API and fetch the data to `CardSwipeAdapter`(discuss later). The `HomeFragmentBinding` is derived from `ViewBinding`, which is a type to bind the view in XML layout (`home_fragment.xml`) into Java field. The swiping function mentioned above is implemented by the `homeCardStackView` inside `HomeFragmentBinding`, which has two main instances: `CardSwipeAdapter` and `CardStackLayoutManager`. The `CardSwipeAdapter` creates `CardSwipeViewHolder` which holds all the field from card item view and is binded with the news articles provided from `HomeViewModel`. Lastly, the `CardStackLayoutManager` manages the data binding to view holder process.  

Notice that the `HomeFragment` implements `CardStackListener`, the `CardStackLayoutManager` will notify `HomeFragment` when there is an event for card swiping, this is essential for collecting user like/unlike actions for further usage.

#### 2. Search Fragment

#### 3. Save Fragment

---
### *Database*

 
### *Deployment*
