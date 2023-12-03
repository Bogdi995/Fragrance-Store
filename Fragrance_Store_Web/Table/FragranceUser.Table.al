table 50100 "Fragrance User"
{
    Caption = 'Fragrance User';
    DataClassification = ToBeClassified;
    LookupPageId = "Fragrance User List";
    DrillDownPageId = "Fragrance User List";

    fields
    {
        field(1; Email; Text[100])
        {
            Caption = 'Email';
            DataClassification = CustomerContent;
        }
        field(10; Name; Text[30])
        {
            Caption = 'Name';
            DataClassification = CustomerContent;
        }
        field(20; Surname; Text[30])
        {
            Caption = 'Surname';
            DataClassification = CustomerContent;
        }
        field(40; Address; Text[512])
        {
            Caption = 'Address';
            DataClassification = CustomerContent;
        }
        field(50; Password; Blob)
        {
            Caption = 'Password';
            DataClassification = CustomerContent;
        }
        field(60; "Dummy String"; Text[100])
        {
            Caption = 'Dummy String';
            DataClassification = CustomerContent;
        }
    }

    keys
    {
        key(PK; Email)
        {
            Clustered = true;
        }
    }

    trigger OnInsert()
    var
        FragranceManagement: Codeunit "Fragrance Management";
        OStream: OutStream;
    begin
        if not FragranceManagement.IsEncryptionEnabled() then
            CreateEncryptionKey();

        Password.CreateOutStream(OStream);
        OStream.WriteText(Encrypt("Dummy String"));

        "Dummy String" := '';
    end;
}