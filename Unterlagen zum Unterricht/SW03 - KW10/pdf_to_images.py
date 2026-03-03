import fitz
import os

pdf_files = ["O04_IP_Selektion.pdf", "O05_IP_Iteration.pdf"]
out_dir = "images_slides"
os.makedirs(out_dir, exist_ok=True)

for pdf_file in pdf_files:
    if os.path.exists(pdf_file):
        print(f"Processing {pdf_file}...")
        doc = fitz.open(pdf_file)
        for i, page in enumerate(doc):
            pix = page.get_pixmap(dpi=150)
            output_path = os.path.join(out_dir, f"{pdf_file.replace('.pdf', '')}_slide_{i+1:02d}.png")
            pix.save(output_path)
        print(f"Saved {len(doc)} pages for {pdf_file}.")
    else:
        print(f"{pdf_file} not found.")
