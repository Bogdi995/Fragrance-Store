table 50101 "Order"
{
    Caption = 'Order';
    DataClassification = ToBeClassified;
    LookupPageId = "Order List";
    DrillDownPageId = "Order List";

    fields
    {
        field(1; "No."; Code[10])
        {
            Caption = 'No.';
            DataClassification = CustomerContent;
        }

        field(10; "Fragrance ID"; Integer)
        {
            Caption = 'Fragrance ID';
            DataClassification = CustomerContent;
        }

        field(20; "User Email"; Text[100])
        {
            Caption = 'User Email';
            DataClassification = CustomerContent;
            TableRelation = "Fragrance User".Email;
        }

        field(30; "Date and Time"; DateTime)
        {
            Caption = 'Date and Time';
            DataClassification = CustomerContent;
        }
    }

    keys
    {
        key(PK; "No.")
        {
            Clustered = true;
        }
    }
}