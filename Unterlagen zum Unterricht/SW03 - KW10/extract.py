import sys
import glob

try:
    import fitz  # PyMuPDF
except ImportError:
    print("PyMuPDF not installed.")
    sys.exit(1)

for pdf_file in glob.glob("*.pdf"):
    try:
        doc = fitz.open(pdf_file)
        text = ""
        for page in doc:
            text += page.get_text()
        with open(pdf_file + ".txt", "w", encoding="utf-8") as f:
            f.write(text)
        print(f"Extracted {pdf_file}")
    except Exception as e:
        print(f"Error extracting {pdf_file}: {e}")
