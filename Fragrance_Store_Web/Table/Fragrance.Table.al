table 50102 Fragrance
{
    Caption = 'Fragrance';
    DataClassification = ToBeClassified;
    LookupPageId = "Fragrance List";
    DrillDownPageId = "Fragrance List";

    fields
    {
        field(1; ID; Code[10])
        {
            Caption = 'ID';
            DataClassification = CustomerContent;
        }
        field(10; Name; Text[75])
        {
            Caption = 'Name';
            DataClassification = CustomerContent;
        }
        field(20; Brand; Enum Brand)
        {
            Caption = 'Brand';
            DataClassification = CustomerContent;
        }
        field(30; Image; Blob)
        {
            Caption = 'Image';
            Subtype = Bitmap;
            DataClassification = CustomerContent;
        }
        field(40; Price; Decimal)
        {
            Caption = 'Price';
            DataClassification = CustomerContent;

            trigger OnValidate()
            var
                FragranceManagement: Codeunit "Fragrance Management";
            begin
                if Quantity <> Quantity::" " then
                    FragranceManagement.SetPricePerAmount(Rec);
            end;
        }
        field(50; Concentration; Enum Concentration)
        {
            Caption = 'Concentration';
            DataClassification = CustomerContent;
        }
        field(60; Quantity; Enum Quantity)
        {
            Caption = 'Quantity';
            DataClassification = CustomerContent;

            trigger OnValidate()
            var
                FragranceManagement: Codeunit "Fragrance Management";
            begin
                if Price <> 0 then
                    FragranceManagement.SetPricePerAmount(Rec);
            end;
        }
        field(70; "Price per Amount"; Decimal)
        {
            Caption = 'Price per Amount';
            Editable = false;
            DataClassification = CustomerContent;
        }
        field(80; "Base Notes"; Text[256])
        {
            Caption = 'Base Notes';
            DataClassification = CustomerContent;
        }
        field(90; "Mid Notes"; Text[256])
        {
            Caption = 'Mid Notes';
            DataClassification = CustomerContent;
        }
        field(100; "Top Notes"; Text[256])
        {
            Caption = 'Top Notes';
            DataClassification = CustomerContent;
        }
    }

    keys
    {
        key(PK; ID)
        {
            Clustered = true;
        }

        key(FK; Name, Brand, Concentration, Quantity)
        {
        }
    }
}