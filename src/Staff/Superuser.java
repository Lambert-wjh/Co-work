package Staff;

import Permission.Superuser.*;

public class Superuser extends Leader implements Staff_Checkable,
    Staff_Modifiable,
    Project_Checkable,
    Project_Modifiable {
}
