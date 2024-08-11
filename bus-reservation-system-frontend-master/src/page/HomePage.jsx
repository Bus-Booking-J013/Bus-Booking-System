import Carousel from "./Carousel";
import Footer from "./Footer";
import { Link } from "react-router-dom";
import travel_1 from "../images/travel_1.png";
import travel_2 from "../images/travel_2.png";

const HomePage = () => {
  return (
    <div className="container-fluid mb-2">
      <Carousel />

      <div className="container mt-5">
        <div className="row">
          <div className="col-md-8">
            <h1 className="text-color">Welcome to Bus Reservation System</h1>
            <p>
              Welcome to our Bus Reservation System, where effortless travel
              planning and exciting journeys converge. Begin your adventure of
              convenience as you navigate our intuitive platform, discovering a
              plethora of routes, destinations, and seating choices. Whether
              you're a frequent commuter or a first-time traveler, our system
              empowers you to book your bus tickets seamlessly, guaranteeing a
              stress-free experience from departure to arrival.
            </p>
            <p>
              As you enter our digital hub, your travel aspirations draw nearer.
              Join us in revolutionizing the bus booking experience,
              transforming every journey into a remarkable destination of its
              own.
            </p>
            <Link to="/user/login" className="btn text-color custom-bg">
              <b> Get Started</b>
            </Link>
          </div>
          <div className="col-md-4">
            <img
              src={travel_2}
              alt="Logo"
              width="400"
              height="auto"
              className="home-image"
            />
          </div>
        </div>

        <div className="row mt-5">
          <div className="col-md-4">
            <img
              src={travel_1}
              alt="Logo"
              width="400"
              height="auto"
              className="home-image"
            />
          </div>
          <div className="col-md-8">
            <h1 className="text-color ms-5">
              Real-time Availability and Instant Confirmation
            </h1>
            <p className="ms-5">
              Indulge in unparalleled convenience with our real-time
              availability and instant confirmation feature. Bid farewell to
              uncertainty and waiting – our state-of-the-art technology
              guarantees you access to up-to-the-minute bus availability and
              confirmation, empowering you to solidify your travel arrangements
              promptly. With just a few clicks, explore bus options, choose your
              desired seating, and receive immediate confirmation, propelling
              you towards your next excursion without hesitation.
            </p>
            <p className="ms-5">
              Furthermore, our system provides instant confirmation for bus
              bookings. Upon completing their reservation, users promptly
              receive an email or notification confirming their booking,
              ensuring their tickets are secured and prepared for travel. This
              eradicates any doubts and enables customers to proceed confidently
              with their travel arrangements.
            </p>
            <Link to="/user/login" className="btn text-color custom-bg ms-5">
              <b> Get Started</b>
            </Link>
          </div>
        </div>
      </div>
      <hr />
      <Footer />
    </div>
  );
};

export default HomePage;
