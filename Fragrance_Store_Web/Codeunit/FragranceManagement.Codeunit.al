codeunit 50100 "Fragrance Management"
{
    procedure IsEncryptionEnabled(): Boolean
    begin
        if EncryptionEnabled() then
            if EncryptionKeyExists() then
                exit(true);

        exit(false);
    end;

    procedure CheckUserPassword(Email: Text; Password: Text): Boolean
    var
        FragranceUser: Record "Fragrance User";
    begin
        FragranceUser.SetCurrentKey(Email);
        FragranceUser.SetRange(Email, Email);

        if FragranceUser.FindFirst() then
            if Password = GetPassword(FragranceUser) then
                exit(true);

        exit(false);
    end;

    local procedure GetPassword(var FragranceUser: Record "Fragrance User"): Text
    var
        StreamReader: DotNet StreamReader;
        IStream: InStream;
        StreamAsText: Text;
    begin
        if not IsEncryptionEnabled() then
            exit;

        FragranceUser.CalcFields(Password);
        FragranceUser.Password.CreateInStream(IStream);
        StreamReader := StreamReader.StreamReader(IStream);

        if not IsNull(StreamReader) then
            if StreamReader.BaseStream.Length > 0 then begin
                StreamAsText := StreamReader.ReadToEnd();
                exit(Decrypt(StreamAsText));
            end;
    end;

    procedure SetPricePerAmount(var Fragrance: Record Fragrance)
    begin
        case Fragrance.Quantity of
            Quantity::"30 ml":
                Fragrance."Price per Amount" := (Fragrance.Price * 100) / 30;
            Quantity::"50 ml":
                Fragrance."Price per Amount" := (Fragrance.Price * 100) / 50;
            Quantity::"60 ml":
                Fragrance."Price per Amount" := (Fragrance.Price * 100) / 60;
            Quantity::"75 ml":
                Fragrance."Price per Amount" := (Fragrance.Price * 100) / 75;
            Quantity::"100 ml":
                Fragrance."Price per Amount" := (Fragrance.Price * 100) / 100;
            Quantity::"125 ml":
                Fragrance."Price per Amount" := (Fragrance.Price * 100) / 125;
            Quantity::"150 ml":
                Fragrance."Price per Amount" := (Fragrance.Price * 100) / 150;
            Quantity::"180 ml":
                Fragrance."Price per Amount" := (Fragrance.Price * 100) / 180;
            Quantity::"200 ml":
                Fragrance."Price per Amount" := (Fragrance.Price * 100) / 200;
            Quantity::"250 ml":
                Fragrance."Price per Amount" := (Fragrance.Price * 100) / 250;
        end;

        Fragrance.Modify(true);
    end;
}