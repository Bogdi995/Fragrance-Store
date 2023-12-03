page 50105 "Order API"
{
    PageType = API;
    Caption = 'Order Api';
    APIPublisher = 'blaga';
    APIGroup = 'store';
    APIVersion = 'v1.0';
    EntityName = 'order';
    EntitySetName = 'orders';
    SourceTable = "Order";
    DelayedInsert = true;
    ODataKeyFields = SystemId;

    // http://localhost:3048/BC210/api/blaga/store/v1.0/orders

    layout
    {
        area(Content)
        {
            group(Control1)
            {
                field(systemId; Rec.SystemId)
                {
                    Caption = 'SystemId';
                }
                field(no; Rec."No.")
                {
                    Caption = 'No.';
                }
                field(fragranceID; Rec."Fragrance ID")
                {
                    Caption = 'Fragrance ID';
                }
                field(userEmail; Rec."User Email")
                {
                    Caption = 'User Email';
                }
                field(dateAndTime; Rec."Date and Time")
                {
                    Caption = 'Date and Time';
                }
            }
        }
    }
}