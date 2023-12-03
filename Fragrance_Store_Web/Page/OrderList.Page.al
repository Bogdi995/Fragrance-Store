page 50103 "Order List"
{
    PageType = List;
    ApplicationArea = All;
    UsageCategory = Administration;
    SourceTable = "Order";
    Caption = 'Order List';
    CardPageId = "Order Card";

    layout
    {
        area(Content)
        {
            repeater(Control1)
            {
                field("No."; Rec."No.")
                {
                    ToolTip = 'Specifies the order number.';
                    ApplicationArea = All;
                }
                field("Fragrance ID"; Rec."Fragrance ID")
                {
                    ToolTip = 'Specifies the fragrance ID';
                    ApplicationArea = All;
                }
                field("User Email"; Rec."User Email")
                {
                    ToolTip = 'Specifies the user email';
                    ApplicationArea = All;
                }
                field("Date and Time"; Rec."Date and Time")
                {
                    ToolTip = 'Specifies the date and time when the order was given';
                    ApplicationArea = All;
                }
            }
        }
    }
}