# RouteTrace3000
Application for generation of events describing vehicle traffic and managing routes/vehicles.

## Prerequisites:

* Geocoding is done using OpenCage.

* Graphhopper is responsible for determining the route.

* The visualization of the designated route is dealt with by Leafletjs.

* The css Bootstrap framework was used for the better appearance of the pages.

* The system distinguishes three categories of events:
    - WORK          A basic event describing work without moving. It has no place to end the event.
                    Represented by the Event class. It is the base class for the two remaining events.
    - MOVE          Describing the movement of the vehicle. In addition, it has a list of points representing the route traveled.
                    Represented by the MoveEvent class. Inherits from the Event class.
    - SPECIFIC      Describes special events. It also has a key-value map that represents special values for the event.
                    Represented by the SpecificEvent class. Inherits from the Event class.

* The route is downloaded on the server side by the 'pulled out' link from the lrm-graphopper (the gpUrl variable has been added to the Routing),
    the following parameters are added to the link: instructions=false so that you do not get instructions and points_encoded=false to get non-coded waypoints.

* The key-value for the SPECIFIC type event are coded on the browser side to the key: value; key: value; and then decoded on the server side.

* The vehicles are represented by the Car class. It has such properties as: make, model, registration number and type.
    - The vehicle types are represented by the enum Vehicle Type

* The Address class represents the location returned from OpenCage. If there are 'undefinded' fields, this means no information for eg street.

* Events can only be added and removed.

* The functionality of adding a new event is carried out on one page. POST/GET for external services are carried out via jQuery.

* Removal from the vehicle system will delete all events assigned to it.

* All text content is implemented using the MessageSource. (i18)

* DTO transfer objects were used to add events.

* REST api enables to add, delete and access events (XML, JSON).

* Each event can be downloaded in the xml format.

Author
Maciej Cyrka
