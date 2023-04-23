import NavTabs from "./NavTabs";
import TabPane1 from "./TabPane1";
import TabPane2 from "./TabPane2";
import TabPane3 from "./TabPane3";

export default function Schedule() {
  return (
    <div className="container">
      <div className="row me-row schedule" id="schedule">
        <h2 className="row-title content-ct">Event Schedule</h2>
        <div className="col-md-12">
          <NavTabs />

          <div className="tab-content">
            <TabPane1 />
            <TabPane2 />
            <TabPane3 />
          </div>
        </div>
      </div>
    </div>
  );
}